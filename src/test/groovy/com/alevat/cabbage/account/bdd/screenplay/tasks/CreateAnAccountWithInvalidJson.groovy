package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.serenitybdd.screenplay.rest.actions.Post
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.thucydides.core.annotations.Step

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat
import static net.serenitybdd.screenplay.Tasks.instrumented

class CreateAnAccountWithInvalidJson implements Task {

    private static final INVALID_ACCOUNT_JSON = '{"notValidAccountJson": ""}'

    @Step("{0} creates an account with invalid JSON")
    @Override
    <T extends Actor> void performAs(T theRestClient) {
        givenThat(theRestClient).wasAbleTo(
            postTheInvalidRequest()
        )
    }

    private Post postTheInvalidRequest() {
        Post
                .toPath("/accounts")
                .withBody(INVALID_ACCOUNT_JSON)
    }

    static CreateAnAccountWithInvalidJson renameThis() {
        return instrumented(CreateAnAccountWithInvalidJson)
    }

}
