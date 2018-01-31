package com.alevat.cabbage.account.service.transaction;

import com.alevat.cabbage.account.service.TransactionService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class TransactionServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TransactionService.class).to(TransactionServiceImpl.class).in(Singleton.class);
    }

}
