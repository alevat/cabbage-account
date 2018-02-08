package alevat.cabbage.account.bdd.cucumber.remote

import com.alevat.cabbage.account.bdd.cucumber.steps.AccountSteps
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class RemoteAccountSteps extends AccountSteps {

    @When("^I create an account with invalid JSON")
    def createAnAccountWithInvalidJson() {
        super.createAnAccountWithInvalidJson()
    }

    @Then("^the response should indicate an invalid request")
    def responseShouldIndicateInvalidRequest() {
        super.responseShouldIndicateInvalidRequest()
    }

}
