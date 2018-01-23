package com.alevat.cabbage.account.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

import static com.alevat.cabbage.account.rest.AbstractResourceHandler.noHandlerException;

@Component
public class AccountMicroserviceRequestHandler
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Inject
    private List<AbstractResourceHandler> handlers;

    @Inject
    private JsonHelper jsonHelper;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
        try {
            return dispatch(requestEvent, context);
        } catch (InvalidRequestException e) {
            Message message = new Message(e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withBody(jsonHelper.toJson(message))
                    .withStatusCode(HttpStatus.SC_BAD_REQUEST);
        }
    }

    private APIGatewayProxyResponseEvent dispatch(APIGatewayProxyRequestEvent requestEvent, Context context) {
        for (AbstractResourceHandler handler : handlers) {
            if (handler.isHandlerFor(requestEvent)) {
                return handler.handleRequest(requestEvent, context);
            }
        }
        throw noHandlerException(requestEvent);
    }

}
