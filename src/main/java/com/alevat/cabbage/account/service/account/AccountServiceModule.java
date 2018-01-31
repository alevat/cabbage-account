package com.alevat.cabbage.account.service.account;

import com.alevat.cabbage.account.service.AccountService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AccountServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AccountService.class).to(AccountServiceImpl.class).in(Singleton.class);
    }

}
