package com.abbink.sws2.api.service;

import com.abbink.sws2.api.service.greeting.GreetingService;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GreetingService.class);
    }
}
