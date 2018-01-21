package com.alevat.cabbage.account.bdd.cucumber.springboot

import com.alevat.aws.lambda.test.SpringBootLambdaProxy
import com.alevat.cabbage.account.ApplicationConfiguration
import com.alevat.cabbage.account.bdd.cucumber.steps.TransactionsStepImplementations
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.restassured.RestAssured
import net.thucydides.core.annotations.Steps
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

import javax.inject.Inject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringBootLambdaProxy)
@ContextConfiguration(classes = [ApplicationConfiguration, TestConfig])
class SpringBootTransactionsStepDefinitions {

    @Steps
    TransactionsStepImplementations implementations

    @Inject
    @LocalServerPort
    private Integer serverPort

    @Before
    def "Configure the test port"() {
        RestAssured.baseURI = "http://localhost:" + serverPort
    }

    @Given("^I have an account")
    def "I have an account"()  {
        implementations."I have an account"()
    }

    @When("^I post a transaction to an account")
    def "I post a transaction to an account"() {
        implementations."I post a transaction to an account"()
    }

    @Then("^the transaction should be listed in the account's ledger")
    def "the transaction should be listed in the account's ledger"() {
        implementations."the transaction should be listed in the account's ledger"()
    }

}
