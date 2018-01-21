package com.alevat.aws.lambda.test

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StubConfig {

    @Bean
    RequestHandler getRequestHandler() {
        return new RequestHandler() {
            @Override
            Object handleRequest(Object input, Context context) {
                return null
            }
        }
    }

}
