package com.alevat.serenitybdd.screenplay.rest.actions

import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import net.serenitybdd.rest.SerenityRest
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

class TheHttpResponse {

    static Question getStatusCode() {
        return new Question<Integer>() {
            @Override
            Integer answeredBy(Actor actor) {
                return getResponse().statusCode()
            }
        }
    }

    private static ExtractableResponse<Response> getResponse() {
        SerenityRest.then().extract()
    }

}
