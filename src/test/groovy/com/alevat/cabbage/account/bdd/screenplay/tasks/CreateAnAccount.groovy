package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.serenitybdd.screenplay.rest.actions.Post
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.thucydides.core.annotations.Step
import org.apache.http.HttpStatus

import static com.alevat.serenitybdd.screenplay.Performables.and
import static net.serenitybdd.screenplay.GivenWhenThen.*
import static net.serenitybdd.screenplay.Tasks.instrumented
import static org.hamcrest.Matchers.is

class CreateAnAccount implements Task {

    private String theRequestedName

    CreateAnAccount withAccountName(accountName) {
        theRequestedName = accountName
        return this
    }

    @Step("{0} creates an account named #theRequestedName")
    @Override
    <T extends Actor> void performAs(T theRestClient) {
        givenThat(theRestClient).wasAbleTo(
                postToTheCreateEndpoint(),
                and(),
                loadTheCurrentAccount()
        )
        then(theRestClient).should(seeThat(TheCurrentAccount.name(), is(theRequestedName)))
    }

    private Post postToTheCreateEndpoint() {
        Post.toPath("/accounts")
                .withQueryParameter("time", "evening")
                .withExpectedStatusCode(HttpStatus.SC_CREATED)
    }

    private TheCurrentAccount loadTheCurrentAccount() {
        TheCurrentAccount.load()
    }

    static CreateAnAccount named(accountName) {
        return instrumented(CreateAnAccount).withAccountName(accountName)
    }
}
