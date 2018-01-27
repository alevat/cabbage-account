package com.alevat.serenitybdd.screenplay.rest.actions

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.restassured.http.ContentType
import net.serenitybdd.screenplay.Interaction
import org.apache.commons.lang3.StringUtils
import org.apache.http.HttpStatus

abstract class AbstractHttpMethod implements Interaction {

    String path
    Map<String, String> queryParameters = new HashMap()
    int expectedStatusCode = HttpStatus.SC_OK
    ContentType contentType = ContentType.JSON
    String body = StringUtils.EMPTY
    private static ObjectMapper objectMapper;

    AbstractHttpMethod(path)
    {
        this.path = path
    }

    AbstractHttpMethod withQueryParameter(String parameterName, String value) {
        queryParameters.put(parameterName, value)
        return this
    }

    AbstractHttpMethod withExpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode
        return this
    }

    AbstractHttpMethod withContentType(ContentType contentType) {
        this.contentType = contentType
        return this
    }

    AbstractHttpMethod withBody(Object body) {
        this.body = getObjectMapper().writeValueAsString(body)
        return this
    }

    static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper()
            objectMapper.registerModule(new JavaTimeModule())
        }
        return objectMapper;
    }
}
