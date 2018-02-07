package com.alevat.cabbage.account.service;

import java.util.List;
import java.util.UUID;

import com.alevat.cabbage.account.service.dto.TransactionDto;

public interface TransactionService {

    TransactionDto create(UUID accountId, TransactionDto transactionDto);

    List<TransactionDto> get(UUID accountId);

}
