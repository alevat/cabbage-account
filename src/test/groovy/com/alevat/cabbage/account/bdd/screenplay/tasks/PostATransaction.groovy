package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.service.dto.TransactionDTO
import com.alevat.serenitybdd.screenplay.rest.actions.Post
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.thucydides.core.annotations.Step
import net.thucydides.core.annotations.Steps

import static net.serenitybdd.screenplay.Tasks.instrumented

class PostATransaction implements Task {

    @Steps
    private TheCurrentAccount theCurrentAccount

    private TransactionDTO transactionDetails

    PostATransaction withTransactionDetails(TransactionDTO transactionDetails) {
        this.transactionDetails = transactionDetails
        return this
    }

    @Step("{0} posts a transaction to the account named #theCurrentAccount.name")
    @Override
    <T extends Actor> void performAs(T theRestClient) {
        theRestClient.wasAbleTo(
                Post.toPath(createTransactionPath()).withBody(transactionDetails)
        )
    }

    String createTransactionPath() {
        return "/accounts/" + theCurrentAccount.id + "/transactions"
    }

    static PostATransaction with(TransactionDTO transactionDetails) {
        return instrumented(PostATransaction).withTransactionDetails(transactionDetails)

    }

}
