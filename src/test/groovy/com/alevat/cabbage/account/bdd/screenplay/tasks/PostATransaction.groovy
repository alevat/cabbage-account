package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.service.dto.TransactionDTO
import com.alevat.cabbage.account.service.dto.TransactionType
import com.alevat.serenitybdd.screenplay.rest.actions.Post
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.thucydides.core.annotations.Step
import net.thucydides.core.annotations.Steps
import org.apache.http.HttpStatus

import java.time.OffsetDateTime

import static net.serenitybdd.screenplay.Tasks.instrumented

class PostATransaction implements Task {

    @Steps
    private TheCurrentAccount theCurrentAccount

    private TransactionDTO transactionDetails = new TransactionDTO()

    PostATransaction withAmount(BigDecimal amount) {
        transactionDetails.amount = Math.abs(amount)
        transactionDetails.type = amount < 0 ? TransactionType.DEBIT : TransactionType.CREDIT
        transactionDetails.timestamp = OffsetDateTime.now()
        return this

    }

    @Step("{0} posts a transaction to the account named #theCurrentAccount.name")
    @Override
    <T extends Actor> void performAs(T theRestClient) {
        theRestClient.wasAbleTo(
                Post
                    .toPath(createTransactionPath())
                    .withBody(transactionDetails)
                    .withExpectedStatusCode(HttpStatus.SC_CREATED)
        )
    }

    String createTransactionPath() {
        return "/accounts/" + theCurrentAccount.id + "/transactions"
    }

    static PostATransaction forAmount(BigDecimal amount) {
        return instrumented(PostATransaction).withAmount(amount)

    }

    PostATransaction toTheCurrentAccount() {
        transactionDetails.accountId = theCurrentAccount.id
        return this
    }
}
