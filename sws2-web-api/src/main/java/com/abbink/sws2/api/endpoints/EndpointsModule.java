package com.abbink.sws2.api.endpoints;

import com.abbink.sws2.api.endpoints.greeting.GreetingEndpoint;
import com.abbink.sws2.common.di.JerseyTypesModule;

public class EndpointsModule extends JerseyTypesModule {

    @Override
    protected void configure() {
        bindJerseyClass(GreetingEndpoint.class);
    }
}
