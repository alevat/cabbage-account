package com.alevat.cabbage.account.bdd.cucumber.springboot

import com.alevat.aws.lambda.test.SpringBootLambdaProxy
import com.alevat.cabbage.account.bdd.cucumber.steps.ImportSteps
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringBootLambdaProxy)
@ContextConfiguration(classes = [SpringBootTestConfiguration])
class SpringBootImportSteps extends ImportSteps {

    @When("^I import a (.*) transaction export file")
    def importTransactionExportFile(String exportTypeName) {
        super.importTransactionExportFile(exportTypeName)
    }

    @Then("^all transaction data in the export file is recorded")
    def exportFileDataIsRecorded() {
        super.exportFileDataIsRecorded()
    }

}
