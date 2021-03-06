package com.alevat.serenitybdd.screenplay.rest.actions

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.response.ResponseBodyExtractionOptions
import io.restassured.response.ValidatableResponse
import io.restassured.specification.RequestSpecification
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Interaction
import net.thucydides.core.annotations.Step
import org.apache.http.HttpStatus

import static net.serenitybdd.rest.SerenityRest.given
import static net.serenitybdd.rest.SerenityRest.then

abstract class RestInvocation implements Interaction {

    String path
    Map<String, String> queryParameters = new HashMap()
    Integer expectedStatusCode = null
    ContentType contentType = ContentType.JSON
    private static ObjectMapper objectMapper;

    RestInvocation(path)
    {
        this.path = path
    }

    static <T> T getResultAs(Class<T> type) {
        return getBody().as(type);
    }

    static <T> List<T> getResultAsListOf(Class<T> type) {
        def content = getBody().asString();
        def collectionType = getObjectMapper().getTypeFactory().constructCollectionType(List, type);
        List<T> values = getObjectMapper().readValue(content, collectionType);
        return values;
    }

    private static ResponseBodyExtractionOptions getBody() {
        then().extract().body()
    }

    RestInvocation withQueryParameter(String parameterName, String value) {
        queryParameters.put(parameterName, value)
        return this
    }

    RestInvocation withExpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode
        return this
    }

    RestInvocation withContentType(ContentType contentType) {
        this.contentType = contentType
        return this
    }

    static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper()
            objectMapper.registerModule(new JavaTimeModule())
        }
        return objectMapper;
    }

    @Override
    @Step("{0} invokes #methodName for #path")
    <T extends Actor> void performAs(T restClient) {
        call()
    }

    abstract ValidatableResponse call()

    abstract String getMethodName()


    RequestSpecification configureRequest() {
        given()
            .contentType(contentType)
            .queryParams(queryParameters);
    }

    ValidatableResponse checkResponse(Response response) {
        if (expectedStatusCode != null)
        {
            response.then().statusCode(expectedStatusCode)
        }
        return response.then()
    }
}
