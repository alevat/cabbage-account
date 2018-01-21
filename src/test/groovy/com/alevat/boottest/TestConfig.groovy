package com.alevat.boottest

import com.alevat.cabbage.account.accounts.AccountsResourceRequestHandler
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
