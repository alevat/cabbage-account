package com.alevat.cabbage.account.rest;

import com.alevat.cabbage.account.service.AccountService;
import com.alevat.cabbage.account.service.dto.AccountDTO;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Handler for /accounts[/${accountId}]
 */
@Component
class AccountsResourceHandler extends AbstractResourceHandler {

    @Inject
    private AccountService accountService;

    @Override
    boolean isHandlerFor(List<String> pathElements) {
        return (pathElements.size() == 1 && pathElements.get(0).equals("accounts"))
                || (pathElements.size() == 2 && pathElements.get(0).equals("accounts"));
    }

    @Override
    APIGatewayProxyResponseEvent doPost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        AccountDTO account = getFromBody(requestEvent, AccountDTO.class);
        account = accountService.create(account);
        return buildResponse(account, HttpStatus.SC_CREATED);
    }

}
