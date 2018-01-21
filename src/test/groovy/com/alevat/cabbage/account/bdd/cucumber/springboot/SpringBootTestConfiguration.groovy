package com.alevat.cabbage.account.bdd.cucumber.springboot

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class SpringBootTestConfiguration {

    @Bean
    AmazonDynamoDB amazonDynamoDB() {
        System.setProperty("sqlite4java.library.path", "build/libs/native")
        return DynamoDBEmbedded.create().amazonDynamoDB();
    }

}
