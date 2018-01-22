package alevat.cabbage.account.bdd.cucumber.remote

import com.alevat.cabbage.account.bdd.cucumber.steps.TransactionsStepImplementations
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import net.thucydides.core.annotations.Steps

class TransactionsStepDefinitions {

    @Steps
    TransactionsStepImplementations implementations

    @Given("^I have an account")
    def iHaveAnAccount()  {
        implementations.iHaveAnAccount()
    }

    @When("^I post a transaction to an account with the amount <amount>")
    def iPostATransactionWith(BigDecimal amount) {
        implementations.iPostATransactionWith(amount)
    }

    @Then("^the transaction should be listed in the account's ledger")
    def theTransactionShouldBeListed() {
        implementations.theTransactionShouldBeListed()
    }

}
