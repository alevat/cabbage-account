package com.alevat.cabbage.account.accounts;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;

public class AccountsResourceRequestHandler
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
        switch (requestEvent.getHttpMethod().toUpperCase()) {
            case "POST":
                return handlePost(requestEvent, context);
            default:
                throw new IllegalArgumentException("No handler for HTTP Method: " + requestEvent.getHttpMethod());
        }
    }

    private APIGatewayProxyResponseEvent handlePost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        return new APIGatewayProxyResponseEvent()
                .withBody("{message: \"Hello there!\"}")
                .withStatusCode(HttpStatus.SC_CREATED);
    }

}
