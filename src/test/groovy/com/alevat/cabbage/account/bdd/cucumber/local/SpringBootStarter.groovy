package com.alevat.cabbage.account.bdd.cucumber.local

import com.alevat.aws.lambda.test.SpringBootLambdaProxy
import com.alevat.boottest.TestConfig
import cucumber.api.java.Before
import io.restassured.RestAssured
import org.junit.rules.ExternalResource
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

import javax.inject.Inject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringBootLambdaProxy)
@ContextConfiguration(classes = [TestConfig, SpringBootLambdaProxy])
class SpringBootStarter extends ExternalResource {

    @Inject
    @LocalServerPort
    private Integer serverPort

    @Before
    def "Configure the test port"() {
        RestAssured.baseURI = "http://localhost:" + serverPort
    }

}
