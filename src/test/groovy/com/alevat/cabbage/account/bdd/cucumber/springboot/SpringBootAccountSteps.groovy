package com.alevat.cabbage.account.bdd.cucumber.springboot;

import com.alevat.aws.lambda.test.SpringBootLambdaProxy
import com.alevat.cabbage.account.bdd.cucumber.steps.AccountSteps
import cucumber.api.java.en.Then
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringBootLambdaProxy)
@ContextConfiguration(classes = [SpringBootTestConfiguration])
class SpringBootAccountSteps extends AccountSteps {

    @When("^I create an account with invalid JSON")
    def createAnAccountWithInvalidJson() {
        super.createAnAccountWithInvalidJson()
    }

    @Then("^the response should indicate an invalid request")
    def responseShouldIndicateInvalidRequest() {
        super.responseShouldIndicateInvalidRequest()
    }

}
