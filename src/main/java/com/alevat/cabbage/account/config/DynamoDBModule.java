package com.alevat.cabbage.account.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class DynamoDBModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AmazonDynamoDB.class).toProvider(AmazonDynamoDBProvider.class).in(Singleton.class);
        bind(DynamoDBMapperConfig.class).toProvider(DynamoDBMapperConfigProvider.class).in(Singleton.class);
        bind(DynamoDBMapper.class).toProvider(DynamoDBMapperProvider.class).in(Singleton.class);
    }
}
