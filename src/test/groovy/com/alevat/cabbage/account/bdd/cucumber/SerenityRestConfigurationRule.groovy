package com.alevat.cabbage.account.bdd.cucumber

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
