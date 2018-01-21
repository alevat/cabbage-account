package com.alevat.cabbage.account.service.account;

import com.alevat.cabbage.account.service.AccountService;
import com.alevat.cabbage.account.service.dto.Account;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Account create(Account account) {
        account.setUuid(UUID.randomUUID());
        return account;
    }

}
