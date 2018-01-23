package com.alevat.cabbage.account.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handler for /accounts/${accountId}/transactions
 */
@Component
class TransactionsResourceHandler extends AbstractResourceHandler {

    @Override
    boolean isHandlerFor(List<String> pathElements) {
        return pathElements.size() == 3
                && pathElements.get(0).equals("accounts")
                && pathElements.get(2).equals("transactions");
    }

    @Override
    APIGatewayProxyResponseEvent doPost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        return buildResponse("", HttpStatus.SC_CREATED);
    }

}
