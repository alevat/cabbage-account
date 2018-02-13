package com.alevat.cabbage.account.bdd.cucumber.steps

import com.alevat.cabbage.account.bdd.screenplay.tasks.ImportATransactionFile
import com.alevat.cabbage.account.bdd.screenplay.tasks.ImportTestFileData
import com.alevat.cabbage.account.domain.ImportFormat
import net.serenitybdd.screenplay.Actor;

class ImportSteps {

    def theClient = Actor.named("Account microservice REST client")

    def importTransactionExportFile(String exportTypeName) {
        ImportTestFileData testFileData = ImportTestFileData.get(exportTypeName)
        ImportFormat format = testFileData.format
        File theExportLocation = testFileData.file
        theClient.attemptsTo(
            ImportATransactionFile.withFormat(format).from(theExportLocation).toTheCurrentAccount()
        )
    }

    def exportFileDataIsRecorded() {
        // TBD
    }
}
