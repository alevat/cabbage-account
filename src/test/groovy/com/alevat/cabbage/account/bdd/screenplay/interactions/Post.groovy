package com.alevat.cabbage.account.bdd.screenplay.interactions

import io.restassured.http.ContentType
import net.serenitybdd.screenplay.Actor
import net.thucydides.core.annotations.Step
import org.apache.commons.lang3.StringUtils
import org.apache.http.HttpStatus

import static net.serenitybdd.rest.SerenityRest.given
import static net.serenitybdd.screenplay.Tasks.instrumented

class Post implements RestInteraction {

    private String path
    private Map<String, String> queryParameters = new HashMap()
    private int expectedStatusCode = HttpStatus.SC_OK
    ContentType contentType = ContentType.JSON
    def body = StringUtils.EMPTY

    Post(path)
    {
        this.path = path
    }

    static def toPath(String path) {
        return instrumented(Post, path)
    }

    @Override
    @Step("{0} POSTs to #path")
    def <T extends Actor> void performAs(T restClient) {
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
