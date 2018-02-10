package com.alevat.cabbage.account.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.google.inject.Provider;

class AmazonDynamoDbProvider implements Provider<AmazonDynamoDB> {

    @Override
    public AmazonDynamoDB get() {
        // Refactor to remove explict region
        return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    }

}
