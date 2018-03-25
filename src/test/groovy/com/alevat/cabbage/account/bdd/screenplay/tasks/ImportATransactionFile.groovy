package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.domain.ImportFormat
import com.alevat.serenitybdd.screenplay.rest.actions.Post
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.thucydides.core.annotations.Step
import net.thucydides.core.annotations.Steps
import org.apache.commons.io.FileUtils
import org.apache.http.HttpStatus

import java.nio.charset.Charset

import static net.serenitybdd.screenplay.Tasks.instrumented

class ImportATransactionFile implements Task {

    ImportFormat format
    private File location
    private UUID accountId

    @Steps
    private TheCurrentAccount theCurrentAccount

    static def withFormat(ImportFormat importFormat) {
        def task = instrumented(ImportATransactionFile)
        task.format = importFormat
        return task
    }

    def from(File location) {
        this.location = location
        return this
    }

    ImportATransactionFile toTheCurrentAccount() {
        accountId = theCurrentAccount.id
        return this
    }

    @Override
    @Step("{0} posts a transaction to the account named #theCurrentAccount.name")
    <T extends Actor> void performAs(T theRestClient) {
        theRestClient.wasAbleTo(
                Post
                    .toPath(createImportPath())
                    .withBody(getImportFileContents())
                    .withExpectedStatusCode(HttpStatus.SC_OK)
        )
    }

    private String getImportFileContents() {
        FileUtils.readFileToString(location, Charset.defaultCharset())
    }

    String createImportPath() {
        return "/accounts/" + accountId + "/transactions/import"
    }
}
