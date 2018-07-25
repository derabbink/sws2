package com.abbink.sws2.api.endpoints;

import com.abbink.sws2.api.endpoints.greeting.GreetingEndpoint;
import com.abbink.sws2.common.di.JerseyModule;

public class EndpointsModule extends JerseyModule {

    @Override
    protected void configure() {
        bindJerseyType(GreetingEndpoint.class);
    }
}
