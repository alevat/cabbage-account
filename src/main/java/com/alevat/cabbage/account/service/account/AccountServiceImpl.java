package com.alevat.cabbage.account.service.account;

import com.alevat.cabbage.account.service.AccountService;
import com.alevat.cabbage.account.service.dto.Account;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Inject
    private AmazonDynamoDB dynamoDB;

    @Override
    public Account create(Account account) {
        account.setId(UUID.randomUUID());
        return account;
    }

}
