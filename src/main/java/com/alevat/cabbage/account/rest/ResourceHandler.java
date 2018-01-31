package com.alevat.cabbage.account.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

interface ResourceHandler {

    boolean isHandlerFor(APIGatewayProxyRequestEvent requestEvent);

    APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context);

}
