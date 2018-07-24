package com.abbink.sws2.webapp.jersey;

import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.Application;

/**
 * Inspired by https://github.com/jersey/jersey/pull/133/
 *
 * Provider needed to wrap {@link Application} instances in {@link ResourceConfig} instance.
 */
public class ResourceConfigProvider implements Provider<ResourceConfig> {

    private final Application application;

    @Inject
    public ResourceConfigProvider(Application application) {
        this.application = application;
    }

    public ResourceConfig get() {
        return ResourceConfig.forApplication(application);
    }
}
