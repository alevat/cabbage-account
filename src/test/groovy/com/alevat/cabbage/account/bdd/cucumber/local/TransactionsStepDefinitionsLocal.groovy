package com.alevat.cabbage.account.bdd.cucumber.local

import com.alevat.aws.lambda.test.SpringBootLambdaProxy
import com.alevat.boottest.TestConfig
import com.alevat.cabbage.account.bdd.screenplay.tasks.CreateAnAccount
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.restassured.RestAssured
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.actors.OnlineCast
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

import javax.inject.Inject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringBootLambdaProxy)
@ContextConfiguration(classes = [TestConfig, SpringBootLambdaProxy])
class TransactionsStepDefinitionsLocal {

    static final String TEST_ACCOUNT_NAME = "Hello there!"

    def theClient = Actor.named("Account microservice REST client")

    @Inject
    @LocalServerPort
    private Integer serverPort

    @Before
    def "Set the stage"() {
        RestAssured.baseURI = "http://localhost:" + serverPort + "/"
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^I have an account")
    def "I have an account"() {
        theClient.wasAbleTo(CreateAnAccount.named(TEST_ACCOUNT_NAME))
    }

    @When("^I post a transaction to an account")
    def "I post a transaction to an account"() {
//        theClient.attemptsTo(PostATransaction.with(details).toAccount(TEST_ACCOUNT_NAME));
    }

    @Then("^the transaction should be listed in the account's ledger")
    def "the transaction should be listed in the account's ledger"() {

    }

}
