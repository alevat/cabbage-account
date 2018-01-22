package com.alevat.cabbage.account.bdd.cucumber.steps

import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccount
import com.alevat.cabbage.account.bdd.screenplay.tasks.TheCurrentAccount
import com.alevat.cabbage.account.service.dto.TransactionDTO
import net.serenitybdd.screenplay.Actor
import net.thucydides.core.annotations.Steps

class TransactionsStepImplementations {

    static final String TEST_ACCOUNT_NAME = "test-account"

    def theClient = Actor.named("AccountDTO microservice REST client")
    def transactionDetails = new TransactionDTO()

    def iHaveAnAccount() {
        if (!TheCurrentAccount.exists()) {
            theClient.wasAbleTo(CreateAnAccount.named(TEST_ACCOUNT_NAME))
        }
    }

    def iPostATransactionWith(BigDecimal amount) {
//        theClient.attemptsTo(PostATransaction.with(transactionDetails).toTheCurrentAccount());
    }

    def theTransactionShouldBeListed() {

    }

}
