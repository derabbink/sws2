package com.abbink.sws2.webapp.application;

import com.abbink.sws2.common.events.AppListener;
import com.codahale.metrics.jmx.JmxReporter;

import javax.inject.Inject;

public class MetricsJmxReporterAppListener implements AppListener {

    private final JmxReporter jmxReporter;

    @Inject
    public MetricsJmxReporterAppListener(JmxReporter jmxReporter) {
        this.jmxReporter = jmxReporter;
    }

    @Override
    public void appStarted() {
        jmxReporter.start();
    }
}
