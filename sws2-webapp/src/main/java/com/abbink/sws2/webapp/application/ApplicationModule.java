package com.abbink.sws2.webapp.application;

import com.abbink.sws2.common.di.AppListenerModule;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.jmx.JmxReporter;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import org.flywaydb.core.Flyway;

public class ApplicationModule extends AbstractModule implements AppListenerModule {

    @Override
    protected void configure() {
        requireBinding(Flyway.class);
        requireBinding(JmxReporter.class);
        // requireBinding(ConsoleReporter.class);

        bindAppListener(DbMigrationAppListener.class);
        bindAppListener(MetricsJmxReporterAppListener.class);
        // bindAppListener(MetricsConsoleReporterAppListener.class);
    }

    @Override
    public Binder binder() {
        return super.binder();
    }
}
