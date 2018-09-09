package com.abbink.sws2.webapp.jersey;

import com.abbink.sws2.common.di.JerseyModule;
import com.abbink.sws2.webapp.jersey.jackson.JacksonProviderWrapper;
import com.abbink.sws2.webapp.jersey.metrics.MetricsApplicationListener;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Provides;

import java.util.concurrent.TimeUnit;

public class JerseyPluginsModule extends AbstractModule implements JerseyModule {
    @Override
    protected void configure() {
        requireBinding(MetricRegistry.class);

        registerMetricsHandler();
        registerJacksonProvider();
    }

    /** hooks metrics into jersey */
    private void registerMetricsHandler() {
        bindJerseyType(MetricsApplicationListener.class);
    }

    /** hooks jackson into jersey */
    private void registerJacksonProvider() {
        bindJerseyType(JacksonProviderWrapper.class);
    }

    @Override
    public Binder binder() {
        return super.binder();
    }
}
