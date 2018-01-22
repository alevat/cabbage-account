package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.service.dto.AccountDTO
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Interaction
import net.serenitybdd.screenplay.Question
import net.serenitybdd.screenplay.annotations.Subject
import net.thucydides.core.annotations.Step

import static net.serenitybdd.rest.SerenityRest.then
import static net.serenitybdd.screenplay.Tasks.instrumented

class TheCurrentAccount implements Interaction {

    @Delegate
    private static AccountDTO account

    static boolean exists() {
        return account != null
    }

    static TheCurrentAccount load() {
        return instrumented(TheCurrentAccount)
    }

    static Question<String> name() {
        return new Question<String>() {
            @Override
            @Subject("the account name")
            String answeredBy(Actor actor) {
                return account.name
            }
        };
    }

    @Override
    @Step("{0} extracts the account from the REST response body")
    <T extends Actor> void performAs(T actor) {
        account = then().extract().body().as(AccountDTO)
    }

}
