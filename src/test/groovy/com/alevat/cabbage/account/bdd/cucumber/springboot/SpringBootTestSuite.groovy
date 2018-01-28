package com.alevat.cabbage.account.bdd.cucumber.springboot

import cucumber.api.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity)
@CucumberOptions(
        plugin = ["pretty"],
        features = "src/test/resources/features"
)
class SpringBootTestSuite {

}
