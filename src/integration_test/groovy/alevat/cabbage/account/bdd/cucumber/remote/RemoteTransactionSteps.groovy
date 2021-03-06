package alevat.cabbage.account.bdd.cucumber.remote

import com.alevat.cabbage.account.bdd.cucumber.steps.TransactionSteps
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class RemoteTransactionSteps extends TransactionSteps {

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
