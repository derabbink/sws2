package com.abbink.sws2.api.endpoints;

import com.abbink.sws2.api.endpoints.greeting.GreetingEndpoint;
import com.abbink.sws2.common.di.JerseyModule;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;

public class EndpointsModule extends AbstractModule implements JerseyModule {

    @Override
    protected void configure() {
        bindJerseyType(GreetingEndpoint.class);
    }

    @Override
    public Binder binder() {
        return super.binder();
    }
}
