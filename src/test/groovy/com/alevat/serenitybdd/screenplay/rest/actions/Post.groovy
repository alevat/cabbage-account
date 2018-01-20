package com.alevat.serenitybdd.screenplay.rest.actions

import io.restassured.http.ContentType
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Interaction
import net.thucydides.core.annotations.Step
import org.apache.commons.lang3.StringUtils
import org.apache.http.HttpStatus

import static net.serenitybdd.rest.SerenityRest.given
import static net.serenitybdd.screenplay.Tasks.instrumented

class Post implements Interaction {

    private String path
    private Map<String, String> queryParameters = new HashMap()
    private int expectedStatusCode = HttpStatus.SC_OK
    private ContentType contentType = ContentType.JSON
    private def body = StringUtils.EMPTY

    Post(path)
    {
        this.path = path
    }

    static def toPath(String path) {
        return instrumented(Post, path)
    }

    @Override
    @Step("{0} invokes POST for #path")
    <T extends Actor> void performAs(T restClient) {
        given()
                .contentType(contentType)
                .queryParams(queryParameters)
                .body(body)
        .when()
                .post(path)
        .then()
            .statusCode(expectedStatusCode)
    }

    Post withQueryParameter(String parameterName, String value) {
        queryParameters.put(parameterName, value)
        return this
    }

    Post withExpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode
        return this
    }

    Post withContentType(ContentType contentType) {
        this.contentType = contentType
        return this
    }
}
