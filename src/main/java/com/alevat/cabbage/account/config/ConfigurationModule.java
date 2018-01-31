package com.alevat.cabbage.account.config;

import com.google.inject.AbstractModule;

public class ConfigurationModule extends AbstractModule {

    @Override
    protected void configure() {
        String stage = System.getenv("STAGE");
        bindConstant().annotatedWith(Stage.class).to(stage);
        String pathPrefix = System.getenv("PATH_PREFIX");
        bindConstant().annotatedWith(PathPrefix.class).to(pathPrefix);
    }

}
