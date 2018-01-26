package com.alevat.cabbage.account.service.transaction;

import com.alevat.cabbage.account.domain.Transaction;
import com.alevat.cabbage.account.service.TransactionService;
import com.alevat.cabbage.account.service.dto.TransactionDTO;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.joda.time.DateTimeUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
class TransactionServiceImpl implements TransactionService {

    @Inject
    private DynamoDBMapper mapper;

    @Override
    public TransactionDTO create(UUID accountId, TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        loadFromDto(transaction, transactionDTO);
        mapper.save(transaction);
        return toDTO(transaction);
    }

    private void loadFromDto(Transaction transaction, TransactionDTO transactionDTO) {
        transaction.setAmount(transactionDTO.getAmount());
        Date timestampDate = Date.from(transactionDTO.getTimestamp().toInstant());
        transaction.setTimestamp(timestampDate);
        transaction.setType(transactionDTO.getType());
    }

    private TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setType(transaction.getType());
        Instant timestampInstant = transaction.getTimestamp().toInstant();
        transactionDTO.setTimestamp(OffsetDateTime.ofInstant(timestampInstant, ZoneId.systemDefault()));
        return transactionDTO;
    }

}
