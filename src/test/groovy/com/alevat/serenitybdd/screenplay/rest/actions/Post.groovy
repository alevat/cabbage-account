package com.alevat.serenitybdd.screenplay.rest.actions

import io.restassured.response.ValidatableResponse
import org.apache.commons.lang3.StringUtils

import static net.serenitybdd.screenplay.Tasks.instrumented

class Post extends RestInvocation {

    private String body = StringUtils.EMPTY

    Post(path)
    {
        super(path)
    }

    static def toPath(String path) {
        return instrumented(Post, path)
    }

    Post withBody(Object body) {
        this.body = getObjectMapper().writeValueAsString(body)
        return this
    }

    @Override
    ValidatableResponse call() {
        def response = configureRequest()
                .body(body)
            .when()
                .post(path)
        checkResponse(response)
    }

    @Override
    String getMethodName() {
        return "POST"
    }
}
