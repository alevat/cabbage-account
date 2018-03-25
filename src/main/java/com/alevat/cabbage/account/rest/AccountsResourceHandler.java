package com.alevat.cabbage.account.rest;

import javax.inject.Inject;

import com.alevat.cabbage.account.config.PathPrefix;
import com.alevat.cabbage.account.service.AccountService;
import com.alevat.cabbage.account.service.dto.AccountDto;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;

@ResourcePath("/accounts")
class AccountsResourceHandler extends AbstractResourceHandler {

    private final AccountService service;

    @Inject
    AccountsResourceHandler(AccountService service, @PathPrefix String pathPrefix, JsonHelper jsonHelper) {
        super(pathPrefix, jsonHelper);
        this.service = service;
    }

    @Override
    APIGatewayProxyResponseEvent doPost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        AccountDto account = getFromBody(requestEvent, AccountDto.class);
        account = service.create(account);
        return buildResponse(account, HttpStatus.SC_CREATED);
    }

}
