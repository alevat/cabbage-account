package com.alevat.serenitybdd.screenplay.rest.actions

import io.restassured.response.ValidatableResponse

import static net.serenitybdd.screenplay.Tasks.instrumented

class Get extends RestInvocation {

    Get(path) {
        super(path)
    }

    static def fromPath(String path) {
        return instrumented(Get, path)
    }

    @Override
    ValidatableResponse call() {
        configureRequest()
        .when()
            .get(path)
        .then()
            .statusCode(expectedStatusCode)
    }

    @Override
    String getMethodName() {
        return "GET"
    }
}
