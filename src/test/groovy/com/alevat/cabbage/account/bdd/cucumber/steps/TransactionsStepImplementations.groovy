package com.alevat.cabbage.account.bdd.cucumber.steps

import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccount
import net.serenitybdd.screenplay.Actor

class TransactionsStepImplementations {

    static final String TEST_ACCOUNT_NAME = "test-account"

    def theClient = Actor.named("Account microservice REST client")

    def "I have an account"() {
        theClient.wasAbleTo(CreateAnAccount.named(TEST_ACCOUNT_NAME))
    }

    def "I post a transaction to an account"() {
//        theClient.attemptsTo(PostATransaction.with(details).toAccount(TEST_ACCOUNT_NAME));
    }

    def "the transaction should be listed in the account's ledger"() {

    }

}
