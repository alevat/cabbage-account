package com.alevat.cabbage.account.bdd.cucumber.steps

import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccount
import com.alevat.cabbage.account.bdd.screenplay.tasks.PostATransaction
import com.alevat.cabbage.account.bdd.screenplay.tasks.TheCurrentAccount
import com.alevat.cabbage.account.bdd.screenplay.tasks.TheCurrentTransaction
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen
import net.thucydides.core.annotations.Steps

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat

class TransactionsStepImplementations {

    static final String TEST_ACCOUNT_NAME = "test-account"

    def theClient = Actor.named("AccountDTO microservice REST client")

    @Steps(shared = true)
    private TheCurrentAccount theCurrentAccount

    def iHaveAnAccount() {
        if (!theCurrentAccount.exists()) {
            theClient.wasAbleTo(CreateAnAccount.named(TEST_ACCOUNT_NAME))
        }
    }

    def iPostATransactionWith(BigDecimal amount) {
        theClient.attemptsTo(PostATransaction.forAmount(amount).toTheCurrentAccount());
    }

    def theTransactionShouldBeListed() {
        theClient.should(seeThat(TheCurrentTransaction.isListedInLedger()))
    }

}
