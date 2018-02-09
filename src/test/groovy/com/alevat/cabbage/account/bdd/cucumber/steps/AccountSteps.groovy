package com.alevat.cabbage.account.bdd.cucumber.steps

import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccountWithInvalidJson
import net.serenitybdd.rest.SerenityRest
import net.serenitybdd.screenplay.Actor
import org.apache.http.HttpStatus

class AccountSteps {

    def theClient = Actor.named("Account microservice REST client")

    def createAnAccountWithInvalidJson() {
        theClient.attemptsTo(CreateAnAccountWithInvalidJson.renameThis())
    }

    def responseShouldIndicateInvalidRequest() {
        // refactor this
        SerenityRest.then().statusCode(HttpStatus.SC_BAD_REQUEST)
    }

}
