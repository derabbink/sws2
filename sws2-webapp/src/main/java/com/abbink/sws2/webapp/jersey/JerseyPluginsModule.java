package com.abbink.sws2.webapp.jersey;

import com.abbink.sws2.common.di.JerseyModule;
import com.abbink.sws2.webapp.jersey.jackson.JacksonProviderWrapper;
import com.abbink.sws2.webapp.jersey.metrics.MetricsApplicationListener;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import com.google.inject.Provides;
import com.google.inject.Scopes;

import java.util.concurrent.TimeUnit;

public class JerseyPluginsModule extends JerseyModule {
    @Override
    protected void configure() {
        registerMetricsHandler();
        registerJacksonProvider();
    }

    @Provides
    public JmxReporter provideMetricsJmxReporter(MetricRegistry registry) {
        return JmxReporter.forRegistry(registry).build();
    }

    @Provides
    public ConsoleReporter provideMetricsConsoleReporter(MetricRegistry registry) {
        return ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();
    }

    /** hooks metrics into jersey */
    private void registerMetricsHandler() {
        bind(MetricRegistry.class).in(Scopes.SINGLETON);
        bindJerseyType(MetricsApplicationListener.class);
    }

    /** hooks jackson into jersey */
    private void registerJacksonProvider() {
        bindJerseyType(JacksonProviderWrapper.class);
    }
}
