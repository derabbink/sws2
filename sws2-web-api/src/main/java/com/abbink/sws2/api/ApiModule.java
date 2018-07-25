package com.abbink.sws2.api;

import com.abbink.sws2.api.endpoints.EndpointsModule;
import com.abbink.sws2.api.service.ServiceModule;
import com.google.inject.AbstractModule;

public class ApiModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ServiceModule());
        install(new EndpointsModule());
    }

}
