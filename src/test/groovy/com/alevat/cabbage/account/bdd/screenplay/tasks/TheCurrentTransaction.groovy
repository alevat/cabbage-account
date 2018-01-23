package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.service.dto.TransactionDTO
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Interaction
import net.thucydides.core.annotations.Step

import static net.serenitybdd.rest.SerenityRest.then
import static net.serenitybdd.screenplay.Tasks.instrumented

class TheCurrentTransaction implements Interaction {

    @Delegate
    static TransactionDTO details = new TransactionDTO()

    static TheCurrentTransaction load() {
        return instrumented(TheCurrentTransaction)
    }

    @Override
    @Step("{0} extracts the transaction from the REST response body")
    <T extends Actor> void performAs(T actor) {
        details = then().extract().body().as(TransactionDTO)
    }

}
