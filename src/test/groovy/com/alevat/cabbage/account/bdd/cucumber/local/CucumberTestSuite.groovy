package com.alevat.cabbage.account.bdd.cucumber.local

import com.alevat.serenitybdd.screenplay.rest.SerenityRestConfigurationRule
import cucumber.api.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.ClassRule
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(CucumberWithSerenity)
@CucumberOptions(
        plugin = ["pretty"],
        features = "src/test/resources/features",
        glue = "com.alevat.cabbage.account.bdd.cucumber.remote"
)
class CucumberTestSuite {

    @ClassRule
    public static SpringBootStarter springBootStarter = new SpringBootStarter()

}
