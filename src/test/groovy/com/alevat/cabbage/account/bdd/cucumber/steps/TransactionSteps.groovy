package com.alevat.cabbage.account.bdd.cucumber.steps

import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccount
import com.alevat.cabbage.account.bdd.screenplay.tasks.PostATransaction
import com.alevat.cabbage.account.bdd.screenplay.tasks.TheCurrentAccount
import com.alevat.cabbage.account.bdd.screenplay.tasks.TheCurrentTransaction
import net.serenitybdd.screenplay.Actor
import net.thucydides.core.annotations.Steps

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat
import static org.hamcrest.core.IsCollectionContaining.hasItem

class TransactionSteps {

    static final String TEST_ACCOUNT_NAME = "test-account"

    def theClient = Actor.named("AccountDto microservice REST client")

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
        theClient.should(seeThat(TheCurrentAccount.ledger(), hasItem(TheCurrentTransaction.details)))
    }

}
