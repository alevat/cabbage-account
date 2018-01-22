package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.serenitybdd.screenplay.rest.actions.Post
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.thucydides.core.annotations.Step
import net.thucydides.core.annotations.Steps

class PostATransaction implements Task {

    @Steps
    private TheCurrentAccount theCurrentAccount

    @Step("{0} posts a transaction to the account named #theCurrentAccount.name")
    @Override
    <T extends Actor> void performAs(T theRestClient) {
        theRestClient.wasAbleTo(
                Post.toPath(createTransactionPath)
                        .withBody(transaction)
        )
    }
}
