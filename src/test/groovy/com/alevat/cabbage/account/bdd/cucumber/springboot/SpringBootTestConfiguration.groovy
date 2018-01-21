package com.alevat.cabbage.account.bdd.cucumber.springboot

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.util.SocketUtils

import javax.inject.Named

@TestConfiguration
class SpringBootTestConfiguration {

    @Bean
    int amazonDynamoDBPort() {
        return SocketUtils.findAvailableTcpPort()
    }

    @Bean
    AmazonDynamoDB amazonDynamoDB(@Named("amazonDynamoDBPort") int port, DynamoDBProxyServer server) {
        def credentials = new BasicAWSCredentials("access", "secret")
        def endpoint = "http://localhost:" + port
        def endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endpoint, null)
        return new AmazonDynamoDBClientBuilder()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }

    @Bean
    DynamoDBProxyServer dynamoDBProxyServer(@Named("amazonDynamoDBPort") int port) {
        DynamoDBProxyServer server = ServerRunner.createServerFromCommandLineArgs(["-inMemory", "-port", port] as String[])
        server.start()
    }

}
