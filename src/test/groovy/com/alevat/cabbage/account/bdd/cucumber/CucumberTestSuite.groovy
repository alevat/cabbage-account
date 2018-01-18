package com.alevat.cabbage.account.bdd.cucumber

import cucumber.api.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.ClassRule
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity)
@CucumberOptions(
        plugin = ["pretty"],
        features = "src/test/resources/features"
)
class CucumberTestSuite {

    @ClassRule
    public static SerenityRestConfigurationRule configurationRule = new SerenityRestConfigurationRule()

}
