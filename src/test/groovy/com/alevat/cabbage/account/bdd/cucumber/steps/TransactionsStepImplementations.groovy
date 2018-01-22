package com.alevat.cabbage.account.bdd.cucumber.steps

import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccount
import com.alevat.cabbage.account.bdd.screenplay.tasks.PostATransaction
import com.alevat.cabbage.account.service.dto.TransactionDTO
import cucumber.api.java.en.Given
import net.serenitybdd.screenplay.Actor

class TransactionsStepImplementations {

    static final String TEST_ACCOUNT_NAME = "test-account"

    def theClient = Actor.named("AccountDTO microservice REST client")
    def transactionDetails = new TransactionDTO()

    def iHaveAnAccount() {
        theClient.wasAbleTo(CreateAnAccount.named(TEST_ACCOUNT_NAME))
    }

    def iPostATransactionWith(BigDecimal amount) {
//        theClient.attemptsTo(PostATransaction.with(transactionDetails).toTheCurrentAccount());
    }

    def theTransactionShouldBeListed() {

    }

}
