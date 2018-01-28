package com.alevat.cabbage.account.domain;

import com.alevat.cabbage.account.service.dto.TransactionType;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@DynamoDBTable(tableName = "cabbage-transaction")
public class Transaction {

    @DynamoDBAutoGeneratedKey
    @DynamoDBRangeKey
    private UUID id;

    @DynamoDBHashKey
    private UUID accountId;

    private BigDecimal amount;

    @DynamoDBTypeConvertedEnum
    private TransactionType type;

    @DynamoDBTypeConvertedTimestamp
    private Date timestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("accountId", accountId)
                .add("amount", amount)
                .add("type", type)
                .add("timestamp", timestamp)
                .toString();
    }
}
