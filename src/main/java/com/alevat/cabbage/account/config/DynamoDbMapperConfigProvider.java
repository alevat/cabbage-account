package com.alevat.cabbage.account.config;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.google.inject.Inject;
import com.google.inject.Provider;

class DynamoDbMapperConfigProvider implements Provider<DynamoDBMapperConfig> {

    private final String stage;

    @Inject
    DynamoDbMapperConfigProvider(@Stage String stage) {
        this.stage = stage;
    }

    @Override
    public DynamoDBMapperConfig get() {
        TableNameOverride tableNameOverride = TableNameOverride.withTableNamePrefix(stage + "-");
        return DynamoDBMapperConfig
                .builder()
                .withTableNameOverride(tableNameOverride)
                .build();
    }

}
