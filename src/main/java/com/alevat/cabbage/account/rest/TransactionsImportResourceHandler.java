package com.alevat.cabbage.account.rest;

import javax.inject.Inject;

import com.alevat.cabbage.account.config.PathPrefix;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;

@ResourcePath("/accounts/*/transactions/import")
class TransactionsImportResourceHandler extends AbstractResourceHandler {

    @Inject
    TransactionsImportResourceHandler(@PathPrefix String pathPrefix, JsonHelper jsonHelper) {
        super(pathPrefix, jsonHelper);
    }

    @Override
    APIGatewayProxyResponseEvent doPost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        return buildResponse(HttpStatus.SC_OK);
    }
}
