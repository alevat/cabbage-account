package com.alevat.cabbage.account.bdd.cucumber.springboot

import com.alevat.aws.lambda.test.SpringBootLambdaProxy
import com.alevat.cabbage.account.bdd.cucumber.steps.TransactionSteps
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.restassured.RestAssured
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

import javax.inject.Inject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringBootLambdaProxy)
@ContextConfiguration(classes = [SpringBootTestConfiguration])
class SpringBootTransactionSteps extends TransactionSteps {

    @Inject
    @LocalServerPort
    private Integer serverPort

    @Before
    def setUp() {
        RestAssured.baseURI = "http://localhost:" + serverPort +"/v1"
    }

    @Given("^I have an account")
    def iHaveAnAccount()  {
        super.iHaveAnAccount()
    }

    @When("^I post a transaction to an account with the amount (.*)")
    def iPostATransactionWith(BigDecimal amount) {
        super.iPostATransactionWith(amount)
    }

    @Then("^the transaction should be listed in the account's ledger")
    def theTransactionShouldBeListed() {
        super.theTransactionShouldBeListed()
    }

}
