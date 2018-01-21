package com.alevat.cabbage.account;

import com.alevat.cabbage.account.service.AccountService;
import com.alevat.cabbage.account.service.dto.Account;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.inject.Inject;

public class AccountsResourceRequestHandler
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Inject
    private AccountService accountService;

    public AccountsResourceRequestHandler() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ResourceConfiguration.class);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

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
        Account account = new Account();
        account.setName("test-account");
        account = accountService.create(account);
        return new APIGatewayProxyResponseEvent()
                .withBody("{\"name\": \"" + account.getName() + "\"}")
                .withStatusCode(HttpStatus.SC_CREATED);
    }

}
