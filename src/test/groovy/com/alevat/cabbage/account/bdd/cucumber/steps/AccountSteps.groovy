package com.alevat.cabbage.account.bdd.cucumber.steps

import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccount
import com.alevat.serenitybdd.screenplay.rest.actions.TheHttpResponse
import net.serenitybdd.screenplay.Actor
import org.apache.http.HttpStatus

import static org.hamcrest.Matchers.is

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat

class AccountSteps {

    def theClient = Actor.named("Account microservice REST client")

    def createAnAccountWithInvalidJson() {
        theClient.attemptsTo(CreateAnAccount.withInvalidJson())
    }

    def responseShouldIndicateInvalidRequest() {
        theClient.should(seeThat(
                "the HTTP response status code",
                TheHttpResponse.statusCode,
                is(HttpStatus.SC_BAD_REQUEST)))
    }

}
