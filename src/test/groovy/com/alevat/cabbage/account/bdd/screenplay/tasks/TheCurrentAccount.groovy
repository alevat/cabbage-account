package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.service.dto.AccountDto
import com.alevat.cabbage.account.service.dto.TransactionDto
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
    private static AccountDto account

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

    static Question<List<TransactionDto>> ledger() {
        return new Question<List<TransactionDto>>() {
            @Override
            @Subject("the transaction ledger")
            List<TransactionDto> answeredBy(Actor actor) {
                Get.fromPath("/accounts/" + account.id + "/transactions")
                        .withExpectedStatusCode(HttpStatus.SC_OK)
                        .call()
                return Get.getResultAsListOf(TransactionDto);
            }
        }
    }

    @Override
    @Step("{0} extracts the account from the current REST response body")
    <T extends Actor> void performAs(T actor) {
        account = RestInvocation.getResultAs(AccountDto)
    }

}
