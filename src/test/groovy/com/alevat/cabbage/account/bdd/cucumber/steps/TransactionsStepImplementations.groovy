package com.alevat.cabbage.account.bdd.cucumber.steps

import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccount
import com.alevat.cabbage.account.bdd.screenplay.tasks.PostATransaction
import com.alevat.cabbage.account.bdd.screenplay.tasks.TheCurrentAccount
import com.alevat.cabbage.account.bdd.screenplay.tasks.TheCurrentTransaction
import com.alevat.cabbage.account.service.dto.TransactionDTO
import com.alevat.cabbage.account.service.dto.TransactionType
import net.serenitybdd.screenplay.Actor
import net.thucydides.core.annotations.Steps

import java.time.OffsetDateTime

class TransactionsStepImplementations {

    static final String TEST_ACCOUNT_NAME = "test-account"

    def theClient = Actor.named("AccountDTO microservice REST client")

    @Steps(shared = true)
    private TheCurrentAccount theCurrentAccount

    @Steps(shared = true)
    private TheCurrentTransaction theCurrentTransaction

    def iHaveAnAccount() {
        if (!theCurrentAccount.exists()) {
            theClient.wasAbleTo(CreateAnAccount.named(TEST_ACCOUNT_NAME))
        }
    }

    def iPostATransactionWith(BigDecimal amount) {
        setUpTheCurrentTransaction(amount)
        theClient.attemptsTo(PostATransaction.with(theCurrentTransaction.details).toTheCurrentAccount());
    }

    private void setUpTheCurrentTransaction(BigDecimal amount) {
        theCurrentTransaction.amount = Math.abs(amount)
        theCurrentTransaction.type = amount < 0 ? TransactionType.DEBIT : TransactionType.CREDIT
        theCurrentTransaction.timestamp = OffsetDateTime.now()
        theCurrentTransaction.accountId = theCurrentAccount.id
    }

    def theTransactionShouldBeListed() {

    }

}
