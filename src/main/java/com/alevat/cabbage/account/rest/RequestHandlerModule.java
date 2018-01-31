package com.alevat.cabbage.account.rest;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class RequestHandlerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ObjectMapper.class).toInstance(getObjectMapper());
        TypeLiteral<RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>> handlerType
                = new TypeLiteral<RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>>() {};
        bind(handlerType).to(AccountMicroserviceRequestHandler.class).in(Singleton.class);
        Multibinder<ResourceHandler> multibinder = Multibinder.newSetBinder(binder(), ResourceHandler.class);
        multibinder.addBinding().to(AccountsResourceHandler.class).in(Singleton.class);
        multibinder.addBinding().to(TransactionsResourceHandler.class).in(Singleton.class);
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

}
