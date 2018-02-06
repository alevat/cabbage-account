package com.alevat.cabbage.account.service;

import com.alevat.cabbage.account.service.dto.TransactionDto;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    TransactionDto create(UUID accountId, TransactionDto transactionDto);

    List<TransactionDto> get(UUID accountId);

}
