package com.alevat.cabbage.account.config;

import com.alevat.cabbage.account.domain.Account;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.inject.Inject;

@Configuration
public class DynamoDBConfiguration {

    @Value("${STAGE}")
    private String stage;

    @Bean
    AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }

    @Bean
    @Inject
    DynamoDBMapper dynamoDBMapper(AmazonDynamoDB dynamoDB, DynamoDBMapperConfig config) throws InterruptedException {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB, config);
        createTables(dynamoDB, mapper, config);
        return mapper;
    }

    @Bean
    DynamoDBMapperConfig dynamoDBMapperConfig() {
        TableNameOverride tableNameOverride = TableNameOverride.withTableNamePrefix(stage + "-");
        return DynamoDBMapperConfig
                .builder()
                .withTableNameOverride(tableNameOverride)
                .build();
    }

    private void createTables(AmazonDynamoDB dynamoDB,
                              DynamoDBMapper mapper,
                              DynamoDBMapperConfig config) throws InterruptedException {
        CreateTableRequest createRequest = mapper.generateCreateTableRequest(Account.class, config);
        ProvisionedThroughput throughput = new ProvisionedThroughput()
                .withReadCapacityUnits(5L)
                .withWriteCapacityUnits(5L);
        createRequest.setProvisionedThroughput(throughput);
        TableUtils.createTableIfNotExists(dynamoDB, createRequest);
        TableUtils.waitUntilActive(dynamoDB, createRequest.getTableName());
    }

}
