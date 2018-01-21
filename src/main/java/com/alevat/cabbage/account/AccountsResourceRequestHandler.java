package com.alevat.cabbage.account;

import com.alevat.cabbage.account.service.AccountService;
import com.alevat.cabbage.account.service.dto.Account;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

@Component
public class AccountsResourceRequestHandler
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Inject
    private AccountService accountService;

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
        try {
            return dispatch(requestEvent, context);
        } catch (InvalidRequestException e) {
            Message message = new Message(e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withBody(toJson(message))
                    .withStatusCode(HttpStatus.SC_BAD_REQUEST);
        }
    }

    private APIGatewayProxyResponseEvent dispatch(APIGatewayProxyRequestEvent requestEvent, Context context) {
        switch (requestEvent.getHttpMethod().toUpperCase()) {
            case "POST":
                return handlePost(requestEvent, context);
            default:
                throw new IllegalArgumentException("No handler for HTTP Method: " + requestEvent.getHttpMethod());
        }
    }

    private APIGatewayProxyResponseEvent handlePost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        Account account = getFromBody(requestEvent, Account.class);
        account = accountService.create(account);
        return new APIGatewayProxyResponseEvent()
                .withBody(toJson(account))
                .withStatusCode(HttpStatus.SC_CREATED);
    }

    private <T> T getFromBody(APIGatewayProxyRequestEvent requestEvent, Class<T> type) {
        String json = requestEvent.getBody();
        if (json == null) {
            throw new InvalidRequestException("Request body was empty");
        }
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new InvalidRequestException("Couldn't deserialize " + json + " as " + type.getName(), e);
        }
    }

    private String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Couldn't serialize as JSON " + object, e);
        }
    }

}
