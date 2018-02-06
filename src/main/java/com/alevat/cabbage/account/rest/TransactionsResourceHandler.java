package com.alevat.cabbage.account.rest;

import com.alevat.cabbage.account.config.PathPrefix;
import com.alevat.cabbage.account.service.TransactionService;
import com.alevat.cabbage.account.service.dto.TransactionDto;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Handler for /accounts/${accountId}/transactions
 */
class TransactionsResourceHandler extends AbstractResourceHandler {

    private static final int ACCOUNT_ID_PATH_PARAM_INDEX = 1;

    private final TransactionService service;

    @Inject
    TransactionsResourceHandler(TransactionService service, @PathPrefix String pathPrefix, JsonHelper jsonHelper) {
        super(pathPrefix, jsonHelper);
        this.service = service;
    }

    @Override
    boolean isHandlerFor(List<String> pathElements) {
        return pathElements.size() == 3
                && pathElements.get(0).equals("accounts")
                && pathElements.get(2).equals("transactions");
    }

    @Override
    APIGatewayProxyResponseEvent doPost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        TransactionDto transactionDto = getFromBody(requestEvent, TransactionDto.class);
        transactionDto = service.create(getAccountId(requestEvent), transactionDto);
        return buildResponse(transactionDto, HttpStatus.SC_CREATED);
    }

    @Override
    APIGatewayProxyResponseEvent doGet(APIGatewayProxyRequestEvent requestEvent, Context context) {
        List<TransactionDto> transactions = service.get(getAccountId(requestEvent));
        return buildResponse(transactions, HttpStatus.SC_OK);
    }

    private UUID getAccountId(APIGatewayProxyRequestEvent requestEvent) {
        String accountIdPathParam = getResourcePathElements(requestEvent).get(ACCOUNT_ID_PATH_PARAM_INDEX);
        return UUID.fromString(accountIdPathParam);
    }

}
