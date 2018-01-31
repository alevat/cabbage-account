package com.alevat.cabbage.account.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.google.inject.Provider;

class AmazonDynamoDBProvider implements Provider<AmazonDynamoDB> {

    @Override
    public AmazonDynamoDB get() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }

}
