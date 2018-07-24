package com.abbink.sws2.webapp.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Inspired by https://github.com/jersey/jersey/pull/133/
 *
 * Almost empty extension of JerseyTypes {@link ServletContainer}, required to mark the servlet as singleton and to inject a
 * properly initialized {@link ResourceConfig} instance.
 */
@Singleton
public class JerseyServlet extends ServletContainer {

    @Inject
    JerseyServlet(ResourceConfig resourceConfig) {
        super(resourceConfig);
    }
}
