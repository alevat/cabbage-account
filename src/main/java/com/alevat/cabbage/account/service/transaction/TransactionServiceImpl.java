package com.alevat.cabbage.account.service.transaction;

import com.alevat.cabbage.account.domain.Transaction;
import com.alevat.cabbage.account.service.TransactionService;
import com.alevat.cabbage.account.service.dto.TransactionDto;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;

class TransactionServiceImpl implements TransactionService {

    private final DynamoDBMapper mapper;

    @Inject
    TransactionServiceImpl(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TransactionDto create(UUID accountId, TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        loadFromDto(transaction, transactionDto);
        mapper.save(transaction);
        return toDto(transaction);
    }

    @Override
    public List<TransactionDto> get(UUID accountId) {
        Map<String, AttributeValue> values = new HashMap<>();
        values.put(":accountId", new AttributeValue().withS(accountId.toString()));
        Map<String, String> names = new HashMap<>();
        names.put("#accountId", "accountId");
        DynamoDBQueryExpression<Transaction> query = new DynamoDBQueryExpression<Transaction>()
                .withKeyConditionExpression("#accountId = :accountId")
                .withExpressionAttributeNames(names)
                .withExpressionAttributeValues(values);
        PaginatedQueryList<Transaction> result = mapper.query(Transaction.class, query);
        return result.stream().map(TransactionServiceImpl::toDto).collect(Collectors.toList());
    }

    private static void loadFromDto(Transaction transaction, TransactionDto transactionDto) {
        transaction.setAmount(transactionDto.getAmount());
        Date timestampDate = Date.from(transactionDto.getTimestamp().toInstant());
        transaction.setTimestamp(timestampDate);
        transaction.setType(transactionDto.getType());
    }

    private static TransactionDto toDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setType(transaction.getType());
        Instant timestampInstant = transaction.getTimestamp().toInstant();
        transactionDto.setTimestamp(OffsetDateTime.ofInstant(timestampInstant, ZoneId.systemDefault()));
        return transactionDto;
    }

}
