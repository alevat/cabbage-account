package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.bdd.screenplay.interactions.Post
import io.restassured.http.ContentType
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.thucydides.core.annotations.Step
import org.apache.http.HttpStatus

import static net.serenitybdd.screenplay.Tasks.instrumented

class CreateAnAccount implements Task {

    String accountName

    CreateAnAccount withAccountName(accountName) {
        this.accountName = accountName
        return this
    }

    @Step("{0} creates an account named #accountName")
    @Override
    def <T extends Actor> void performAs(T restClient) {
        restClient.attemptsTo(
                Post.toPath("/Seattle")
                    .withQueryParameter("time", "evening")
                    .withContentType(ContentType.JSON)
                    .withExpectedStatusCode(HttpStatus.SC_OK)
        );
//        Account account = new Account(name: accountName)
    }

    static CreateAnAccount named(accountName) {
        return instrumented(CreateAnAccount).withAccountName(accountName)
    }
}
