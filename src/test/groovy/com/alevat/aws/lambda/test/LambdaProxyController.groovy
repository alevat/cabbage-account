package com.alevat.aws.lambda.test

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import org.apache.http.entity.ContentType
import org.mockito.Mockito
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

import static org.springframework.web.bind.annotation.RequestMethod.*

@RestController
class LambdaProxyController {

    @Inject
    private RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> requestHandler

    @RequestMapping(path = "/**", method = [GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE])
    ResponseEntity<String> handleRequest(HttpEntity<String> httpEntity, HttpServletRequest servletRequest) {
        APIGatewayProxyRequestEvent requestEvent = buildRequestEvent(httpEntity, servletRequest)
        Context context = Mockito.mock(Context)
        def responseEvent = requestHandler.handleRequest(requestEvent, context)
        return buildResponseEntity(responseEvent)
    }

    APIGatewayProxyRequestEvent buildRequestEvent(HttpEntity<String> httpEntity, HttpServletRequest servletRequest) {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent()
        requestEvent.httpMethod = servletRequest.method
        requestEvent.path = servletRequest.servletPath
        requestEvent.body = httpEntity.body
        return requestEvent
    }

    ResponseEntity<String> buildResponseEntity(APIGatewayProxyResponseEvent responseEvent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON)
        return new ResponseEntity<>(responseEvent.body, headers, responseEvent.statusCode)
    }
}
