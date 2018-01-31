package com.alevat.cabbage.account;

import com.alevat.cabbage.account.rest.RequestHandlerModule;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class BaseHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Inject
    private RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> delegateHandler;

    public BaseHandler() {
        super();
        Injector injector = Guice.createInjector(new RequestHandlerModule());
        injector.injectMembers(this);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        return delegateHandler.handleRequest(input, context);
    }
}
