package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.service.dto.TransactionDto
import com.alevat.cabbage.account.service.dto.TransactionType
import com.alevat.serenitybdd.screenplay.rest.actions.Post
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.Task
import net.thucydides.core.annotations.Step
import net.thucydides.core.annotations.Steps
import org.apache.http.HttpStatus

import java.time.OffsetDateTime

import static com.alevat.serenitybdd.screenplay.Performables.and
import static net.serenitybdd.screenplay.Tasks.instrumented

class PostATransaction implements Task {

    @Steps
    private TheCurrentAccount theCurrentAccount

    private String accountId

    private TransactionDto transactionDetails = new TransactionDto()

    PostATransaction withAmount(BigDecimal amount) {
        transactionDetails.amount = amount.abs();
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
                    .withExpectedStatusCode(HttpStatus.SC_CREATED),
                and(),
                loadTheCurrentTransaction()
        )
    }

    Performable loadTheCurrentTransaction() {
        TheCurrentTransaction.load()
    }

    String createTransactionPath() {
        return "/accounts/" + accountId + "/transactions"
    }

    static PostATransaction forAmount(BigDecimal amount) {
        return instrumented(PostATransaction).withAmount(amount)

    }

    PostATransaction toTheCurrentAccount() {
        accountId = theCurrentAccount.id
        return this
    }
}
