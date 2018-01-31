package com.alevat.cabbage.account.service.transaction;

import com.alevat.cabbage.account.domain.Transaction;
import com.alevat.cabbage.account.service.TransactionService;
import com.alevat.cabbage.account.service.dto.TransactionDTO;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import javax.inject.Inject;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

class TransactionServiceImpl implements TransactionService {

    @Inject
    private DynamoDBMapper mapper;

    @Override
    public TransactionDTO create(UUID accountId, TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        loadFromDTO(transaction, transactionDTO);
        mapper.save(transaction);
        return toDTO(transaction);
    }

    @Override
    public List<TransactionDTO> get(UUID accountId) {
        Map<String, AttributeValue> values = new HashMap<>();
        values.put(":accountId", new AttributeValue().withS(accountId.toString()));
        Map<String, String> names = new HashMap<>();
        names.put("#accountId", "accountId");
        DynamoDBQueryExpression<Transaction> query = new DynamoDBQueryExpression<Transaction>()
                .withKeyConditionExpression("#accountId = :accountId")
                .withExpressionAttributeNames(names)
                .withExpressionAttributeValues(values);
        PaginatedQueryList<Transaction> result = mapper.query(Transaction.class, query);
        return result.stream().map(TransactionServiceImpl::toDTO).collect(Collectors.toList());
    }

    private static void loadFromDTO(Transaction transaction, TransactionDTO transactionDTO) {
        transaction.setAmount(transactionDTO.getAmount());
        Date timestampDate = Date.from(transactionDTO.getTimestamp().toInstant());
        transaction.setTimestamp(timestampDate);
        transaction.setType(transactionDTO.getType());
    }

    private static TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setType(transaction.getType());
        Instant timestampInstant = transaction.getTimestamp().toInstant();
        transactionDTO.setTimestamp(OffsetDateTime.ofInstant(timestampInstant, ZoneId.systemDefault()));
        return transactionDTO;
    }

}
