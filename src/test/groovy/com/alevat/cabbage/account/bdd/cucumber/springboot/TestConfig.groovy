package com.alevat.cabbage.account.bdd.cucumber.springboot

import com.alevat.cabbage.account.AccountsResourceRequestHandler
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {

    @Bean
    RequestHandler getRequestHandler() {
        return new AccountsResourceRequestHandler()
    }

}
