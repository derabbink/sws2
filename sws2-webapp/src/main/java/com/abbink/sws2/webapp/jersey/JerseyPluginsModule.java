package com.abbink.sws2.webapp.jersey;

import com.abbink.sws2.common.di.JerseyTypesModule;

public class JerseyPluginsModule extends JerseyTypesModule {
    @Override
    protected void configure() {
        registerJacksonProvider();
    }

    /** hooks jackson into jersey */
    private void registerJacksonProvider() {
        bindJerseyClass(JacksonProviderWrapper.class);
    }
}
