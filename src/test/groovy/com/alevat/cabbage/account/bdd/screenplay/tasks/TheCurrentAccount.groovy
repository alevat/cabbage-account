package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.service.dto.AccountDTO
import com.alevat.cabbage.account.service.dto.TransactionDTO
import com.alevat.serenitybdd.screenplay.rest.actions.Get
import com.alevat.serenitybdd.screenplay.rest.actions.RestInvocation
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Interaction
import net.serenitybdd.screenplay.Question
import net.serenitybdd.screenplay.annotations.Subject
import net.thucydides.core.annotations.Step
import org.apache.http.HttpStatus

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

    static Question<List<TransactionDTO>> ledger() {
        return new Question<List<TransactionDTO>>() {
            @Override
            @Subject("the transaction ledger")
            List<TransactionDTO> answeredBy(Actor actor) {
                Get.fromPath("/accounts/" + account.id + "/transactions")
                        .withExpectedStatusCode(HttpStatus.SC_OK)
                        .call()
                return Get.getResultAs(List);
            }
        }
    }

    @Override
    @Step("{0} extracts the account from the current REST response body")
    <T extends Actor> void performAs(T actor) {
        account = RestInvocation.getResultAs(AccountDTO)
    }

}
