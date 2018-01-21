package com.alevat.cabbage.account.service.account;

import com.alevat.cabbage.account.domain.Account;
import com.alevat.cabbage.account.service.AccountService;
import com.alevat.cabbage.account.service.dto.AccountDTO;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AccountServiceImpl implements AccountService {

    @Inject
    private DynamoDBMapper mapper;

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
