package com.alevat.cabbage.account.bdd.cucumber.remote

import com.alevat.serenitybdd.screenplay.rest.SerenityRestConfigurationRule
import cucumber.api.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.ClassRule
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity)
@CucumberOptions(
        plugin = ["pretty"],
        features = "src/test/resources/features"
)
class RemoteTestSuite {

    @ClassRule
    public static SerenityRestConfigurationRule configurationRule = new SerenityRestConfigurationRule(
            baseURI: "https://accounts-api-test.cabbage.aws.alevat.com/v1"
    )

}
