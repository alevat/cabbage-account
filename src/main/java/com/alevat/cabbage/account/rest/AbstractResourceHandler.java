package com.alevat.cabbage.account.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.common.base.Splitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
abstract class AbstractResourceHandler {

    private static final Logger LOG = LogManager.getLogger(AbstractResourceHandler.class);

    @Value("${PATH_PREFIX}")
    private String pathPrefix;

    @Inject
    private JsonHelper jsonHelper;

    boolean isHandlerFor(APIGatewayProxyRequestEvent requestEvent) {
        List<String> pathElements = getResourcePathElements(requestEvent);
        return isHandlerFor(pathElements);
    }

    @NotNull
    private List<String> getResourcePathElements(APIGatewayProxyRequestEvent requestEvent) {
        String resourcePath = getResourcePath(requestEvent);
        return Splitter.on('/').omitEmptyStrings().splitToList(resourcePath);
    }

    private String getResourcePath(APIGatewayProxyRequestEvent requestEvent) {
        String path = requestEvent.getPath();
        return path.replaceFirst(pathPrefix, "");
    }

    abstract boolean isHandlerFor(List<String> pathElements);

    final APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
        String method = requestEvent.getHttpMethod();
        switch (method) {
            case "POST":
                return doPost(requestEvent, context);
            case "GET":
                return doGet(requestEvent, context);
            default:
                throw noHandlerException(requestEvent);
        }
    }

    /**
     * Override to handle method
     */
    APIGatewayProxyResponseEvent doPost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        throw noHandlerException(requestEvent);
    }

    /**
     * Override to handle method
     */
    APIGatewayProxyResponseEvent doGet(APIGatewayProxyRequestEvent requestEvent, Context context) {
        throw noHandlerException(requestEvent);
    }

    @NotNull
    static IllegalArgumentException noHandlerException(APIGatewayProxyRequestEvent requestEvent) {
        String message = "No handler for request: "
                + requestEvent.getHttpMethod() + " " + requestEvent.getPath();
        LOG.warn(message);
        throw new InvalidRequestException(message);
    }

    <T> T getFromBody(APIGatewayProxyRequestEvent requestEvent, Class<T> type) {
        String json = requestEvent.getBody();
        if (json == null) {
            throw new InvalidRequestException("Request body was empty");
        }
        return jsonHelper.fromJson(type, json);
    }

    APIGatewayProxyResponseEvent buildResponse(Object body, int httpStatusCode) {
        return new APIGatewayProxyResponseEvent()
                .withBody(jsonHelper.toJson(body))
                .withStatusCode(httpStatusCode);
    }

}
