package com.alevat.cabbage.account.bdd.cucumber.remote

import com.alevat.cabbage.account.bdd.cucumber.steps.TransactionsStepImplementations
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import net.thucydides.core.annotations.Steps

class TransactionsStepDefinitions {

    @Steps
    TransactionsStepImplementations implementations

    @Given("^I have an account")
    def "I have an account"()  {
        implementations."I have an account"()
    }

    @When("^I post a transaction to an account")
    def "I post a transaction to an account"() {
        implementations."I post a transaction to an account"()
    }

    @Then("^the transaction should be listed in the account's ledger")
    def "the transaction should be listed in the account's ledger"() {
        implementations."the transaction should be listed in the account's ledger"()
    }

}
