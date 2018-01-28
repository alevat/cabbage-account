package com.alevat.serenitybdd.screenplay.rest

import io.restassured.RestAssured
import net.serenitybdd.rest.RestDefaultsChained
import org.junit.rules.ExternalResource

class SerenityRestConfigurationRule extends ExternalResource {

    String baseURI

    @Override
    protected void before() throws Throwable {
        RestAssured.baseURI = baseURI
        new RestDefaultsChained().useRelaxedHTTPSValidation()
    }

}
