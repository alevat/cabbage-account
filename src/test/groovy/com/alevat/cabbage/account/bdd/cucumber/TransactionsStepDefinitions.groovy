package com.alevat.cabbage.account.bdd.cucumber

import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccount
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.actors.OnlineCast

class TransactionsStepDefinitions {

    static final String TEST_ACCOUNT_NAME = "test"

    def theClient = Actor.named("Account microservice REST client")

    @Before
    def "Set the stage"() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^I have an account")
    def "I have an account"() {
        theClient.wasAbleTo(CreateAnAccount.named(TEST_ACCOUNT_NAME))
    }

    @When("^I post a transaction to an account")
    def "I post a transaction to an account"() {
//        theClient.attemptsTo(PostATransaction.with(details).toAccount(TEST_ACCOUNT_NAME));
    }

    @Then("^the transaction should be listed in the account's ledger")
    def "the transaction should be listed in the account's ledger"() {

    }

}
