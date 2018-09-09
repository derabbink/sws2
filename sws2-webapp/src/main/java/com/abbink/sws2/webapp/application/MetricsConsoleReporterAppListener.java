package com.abbink.sws2.webapp.application;

import com.abbink.sws2.common.events.AppListener;
import com.codahale.metrics.ConsoleReporter;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class MetricsConsoleReporterAppListener implements AppListener {

    private final ConsoleReporter consoleReporter;

    @Inject
    public MetricsConsoleReporterAppListener(ConsoleReporter consoleReporter) {
        this.consoleReporter = consoleReporter;
    }

    @Override
    public void appStarted() {
        consoleReporter.start(1, TimeUnit.SECONDS);
    }
}
