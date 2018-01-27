package com.alevat.serenitybdd.screenplay.rest.actions

import net.serenitybdd.screenplay.Actor
import net.thucydides.core.annotations.Step

import static net.serenitybdd.rest.SerenityRest.given
import static net.serenitybdd.screenplay.Tasks.instrumented

class Post extends AbstractHttpMethod {

    Post(path)
    {
        super(path)
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

}
