package com.alevat.cabbage.account.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

import static com.alevat.cabbage.account.rest.AbstractResourceHandler.noHandlerException;

class AccountMicroserviceRequestHandler
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger LOG = LogManager.getLogger(AccountMicroserviceRequestHandler.class);

    @Inject
    private List<AbstractResourceHandler> handlers;

    @Inject
    private JsonHelper jsonHelper;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
        try {
            LOG.debug("Dispatching request: " + requestEvent);
            return dispatch(requestEvent, context);
        } catch (InvalidRequestException e) {
            LOG.warn("Invalid request: " + e.getMessage());
            Message message = new Message(e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withBody(jsonHelper.toJson(message))
                    .withStatusCode(HttpStatus.SC_BAD_REQUEST);
        } catch (RuntimeException e) {
            LOG.error("System error handling request " + requestEvent, e);
            Message message = new Message(e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withBody(jsonHelper.toJson(message))
                    .withStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private APIGatewayProxyResponseEvent dispatch(APIGatewayProxyRequestEvent requestEvent, Context context) {
        for (AbstractResourceHandler handler : handlers) {
            if (handler.isHandlerFor(requestEvent)) {
                return handler.handleRequest(requestEvent, context);
            }
        }
        throw noHandlerException(requestEvent);
    }

}
