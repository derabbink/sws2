package com.abbink.sws2.webapp.jersey.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jersey2.InstrumentedResourceMethodApplicationListener;

import javax.inject.Inject;

/** Wapper type annotated with {@link Inject} */
public class MetricsApplicationListener extends InstrumentedResourceMethodApplicationListener {

    @Inject
    public MetricsApplicationListener(MetricRegistry metrics) {
        super(metrics);
    }
}
