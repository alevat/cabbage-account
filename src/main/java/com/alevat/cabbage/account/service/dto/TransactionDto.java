package com.alevat.cabbage.account.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class TransactionDto {

    private UUID id;

    private BigDecimal amount;

    private TransactionType type;

    private OffsetDateTime timestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("amount", amount)
                .add("type", type)
                .add("timestamp", timestamp)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionDto that = (TransactionDto) o;
        return Objects.equals(id, that.id)
                && type == that.type
                && areAmountsEqual(that)
                && Objects.equals(timestamp, that.timestamp);
    }

    private boolean areAmountsEqual(TransactionDto that) {
        if (amount == null || that.amount == null) {
            return amount == that.amount;
        } else {
            return amount.compareTo(that.amount) == 0;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, type, timestamp);
    }
}
