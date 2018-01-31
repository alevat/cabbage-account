package com.alevat.cabbage.account.service.account;

import com.alevat.cabbage.account.domain.Account;
import com.alevat.cabbage.account.service.AccountService;
import com.alevat.cabbage.account.service.dto.AccountDTO;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

class AccountServiceImpl implements AccountService {

    private final DynamoDBMapper mapper;

    @Inject
    AccountServiceImpl(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        Account account = fromDTO(accountDTO);
        mapper.save(account);
        return toDTO(account);
    }

    private Account fromDTO(AccountDTO accountDTO) {
        Account account = new Account();
        account.setName(accountDTO.getName());
        return account;
    }

    private AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        return accountDTO;
    }

}
