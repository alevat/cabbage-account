package com.alevat.cabbage.account.config;

import com.alevat.cabbage.account.domain.Account;
import com.alevat.cabbage.account.domain.Transaction;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.google.inject.Inject;
import com.google.inject.Provider;

class DynamoDbMapperProvider implements Provider<DynamoDBMapper> {

    private final AmazonDynamoDB dynamoDb;
    private final DynamoDBMapperConfig config;

    @Inject
    DynamoDbMapperProvider(AmazonDynamoDB dynamoDb, DynamoDBMapperConfig config) {
        this.dynamoDb = dynamoDb;
        this.config = config;
    }

    @Override
    public DynamoDBMapper get() {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb, config);
        try {
            createTables(dynamoDb, mapper, config);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mapper;
    }

    private void createTables(AmazonDynamoDB dynamoDb,
                              DynamoDBMapper mapper,
                              DynamoDBMapperConfig config) throws InterruptedException {
        createTable(dynamoDb, mapper, config, Account.class);
        createTable(dynamoDb, mapper, config, Transaction.class);
    }

    private void createTable(AmazonDynamoDB dynamoDb,
                             DynamoDBMapper mapper,
                             DynamoDBMapperConfig config,
                             Class<?> tableClass) throws InterruptedException {
        CreateTableRequest createRequest = mapper.generateCreateTableRequest(tableClass, config);
        ProvisionedThroughput throughput = new ProvisionedThroughput()
                .withReadCapacityUnits(5L)
                .withWriteCapacityUnits(5L);
        createRequest.setProvisionedThroughput(throughput);
        TableUtils.createTableIfNotExists(dynamoDb, createRequest);
        TableUtils.waitUntilActive(dynamoDb, createRequest.getTableName());
    }

}
