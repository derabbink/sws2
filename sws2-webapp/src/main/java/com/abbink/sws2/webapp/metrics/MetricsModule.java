package com.abbink.sws2.webapp.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

import java.util.concurrent.TimeUnit;

public class MetricsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MetricRegistry.class).in(Scopes.SINGLETON);
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
}
