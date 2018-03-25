package com.alevat.cabbage.account.rest;

import java.util.List;

import com.alevat.cabbage.account.config.PathPrefix;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.common.base.Splitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class AbstractResourceHandler implements ResourceHandler {

    private static final Logger LOG = LogManager.getLogger(AbstractResourceHandler.class);
    private static final String WILDCARD_PATH_ELEMENT = "*";

    private final String pathPrefix;

    private final JsonHelper jsonHelper;

    AbstractResourceHandler(@PathPrefix String pathPrefix, JsonHelper jsonHelper) {
        this.pathPrefix = pathPrefix;
        this.jsonHelper = jsonHelper;
    }

    public final boolean isHandlerFor(APIGatewayProxyRequestEvent requestEvent) {
        List<String> pathElements = getRequestPathElements(requestEvent);
        return isHandlerFor(pathElements);
    }

    final boolean isHandlerFor(List<String> pathElements) {
        List<String> handlerPathElements = getHandlerPathElements();
        if (pathElements.size() != handlerPathElements.size()) {
            return false;
        }
        for (int i = 0; i < handlerPathElements.size(); i++) {
            String handlerPathElement = handlerPathElements.get(i);
            String pathElement = pathElements.get(i);
            if (!isWildcard(handlerPathElement) && !handlerPathElement.equals(pathElement))     {
                return false;
            }
        }
        return true;
    }

    private List<String> getHandlerPathElements() {
        String handlerPath = getClass().getAnnotation(ResourcePath.class).value();
        return splitPath(handlerPath);
    }

    private boolean isWildcard(String handlerPathElement) {
        return WILDCARD_PATH_ELEMENT.equals(handlerPathElement);
    }

    List<String> getRequestPathElements(APIGatewayProxyRequestEvent requestEvent) {
        String resourcePath = getRequestPath(requestEvent);
        return splitPath(resourcePath);
    }

    private List<String> splitPath(String resourcePath) {
        return Splitter.on('/').omitEmptyStrings().splitToList(resourcePath);
    }

    private String getRequestPath(APIGatewayProxyRequestEvent requestEvent) {
        String path = requestEvent.getPath();
        return path.replaceFirst(pathPrefix, "");
    }

    public final APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
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
     * Override to handle method.
     */
    APIGatewayProxyResponseEvent doPost(APIGatewayProxyRequestEvent requestEvent, Context context) {
        throw noHandlerException(requestEvent);
    }

    /**
     * Override to handle method.
     */
    APIGatewayProxyResponseEvent doGet(APIGatewayProxyRequestEvent requestEvent, Context context) {
        throw noHandlerException(requestEvent);
    }

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

    APIGatewayProxyResponseEvent buildResponse(int httpStatusCode) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(httpStatusCode);
    }

    APIGatewayProxyResponseEvent buildResponse(Object body, int httpStatusCode) {
        return new APIGatewayProxyResponseEvent()
                .withBody(jsonHelper.toJson(body))
                .withStatusCode(httpStatusCode);
    }

}
