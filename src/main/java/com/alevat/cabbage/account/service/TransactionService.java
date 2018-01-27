package com.alevat.cabbage.account.service;

import com.alevat.cabbage.account.service.dto.TransactionDTO;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    TransactionDTO create(UUID accountId, TransactionDTO transactionDTO);

    List<TransactionDTO> get(UUID accountId);

}
