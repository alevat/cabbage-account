package com.alevat.cabbage.account.bdd.cucumber

import io.restassured.RestAssured
import net.serenitybdd.rest.RestDefaultsChained
import org.junit.rules.ExternalResource

class SerenityRestConfigurationRule extends ExternalResource {

    private final String baseURI = "https://accounts-api-test.cabbage.aws.alevat.com/v1"

    @Override
    protected void before() throws Throwable {
        RestAssured.baseURI = baseURI
        new RestDefaultsChained().useRelaxedHTTPSValidation()
    }

}
