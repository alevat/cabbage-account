package com.alevat.cabbage.account.bdd.cucumber.remote

import com.alevat.aws.lambda.test.SpringBootLambdaProxy
import com.alevat.boottest.TestConfig
import com.alevat.serenitybdd.screenplay.rest.SerenityRestConfigurationRule
import cucumber.api.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.ClassRule
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@RunWith(CucumberWithSerenity)
@CucumberOptions(
        plugin = ["pretty"],
        features = "src/test/resources/features"
)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringBootLambdaProxy)
@ContextConfiguration(classes = TestConfig)
class CucumberTestSuite {

    @ClassRule
    public static SerenityRestConfigurationRule configurationRule = new SerenityRestConfigurationRule(
            baseURI: "https://accounts-api-test.cabbage.aws.alevat.com/v1"
    )

}
