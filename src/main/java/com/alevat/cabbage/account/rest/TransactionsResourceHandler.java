package com.alevat.cabbage.account.rest;

import com.alevat.cabbage.account.service.TransactionService;
import com.alevat.cabbage.account.service.dto.TransactionDTO;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Handler for /accounts/${accountId}/transactions
 */
@Component
class TransactionsResourceHandler extends AbstractResourceHandler {

    public static final int ACCOUNT_ID_PATH_PARAM_INDEX = 1;

    @Inject
    private TransactionService service;

    @Override
    boolean isHandlerFor(List<String> pathElements) {
        return pathElements.size() == 3
                && pathElements.get(0).equals("accounts")
                && pathElements.get(2).equals("transactions");
    }

    @Override
    APIGatewayProxyResponseEvent doPost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        TransactionDTO transactionDTO = getFromBody(requestEvent, TransactionDTO.class);
        transactionDTO = service.create(getAccountId(requestEvent), transactionDTO);
        return buildResponse(transactionDTO, HttpStatus.SC_CREATED);
    }

    @Override
    APIGatewayProxyResponseEvent doGet(APIGatewayProxyRequestEvent requestEvent, Context context) {
        List<TransactionDTO> transactions = service.get(getAccountId(requestEvent));
        return buildResponse(transactions, HttpStatus.SC_OK);
    }

    private UUID getAccountId(APIGatewayProxyRequestEvent requestEvent) {
        String accountIdPathParam = getResourcePathElements(requestEvent).get(ACCOUNT_ID_PATH_PARAM_INDEX);
        return UUID.fromString(accountIdPathParam);
    }

}
