package com.alevat.cabbage.account.service.account;

import javax.inject.Inject;

import com.alevat.cabbage.account.domain.Account;
import com.alevat.cabbage.account.service.AccountService;
import com.alevat.cabbage.account.service.dto.AccountDto;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

class AccountServiceImpl implements AccountService {

    private final DynamoDBMapper mapper;

    @Inject
    AccountServiceImpl(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        Account account = fromDto(accountDto);
        mapper.save(account);
        return toDto(account);
    }

    private Account fromDto(AccountDto accountDto) {
        Account account = new Account();
        account.setName(accountDto.getName());
        return account;
    }

    private AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setName(account.getName());
        return accountDto;
    }

}
