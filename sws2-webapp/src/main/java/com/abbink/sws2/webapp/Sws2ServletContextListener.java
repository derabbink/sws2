package com.abbink.sws2.webapp;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.jmx.JmxReporter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.concurrent.TimeUnit;

public class Sws2ServletContextListener extends GuiceServletContextListener {
    private static final Object INJECTOR_LOCK = new Object();
    private static Injector INJECTOR;

    public static Injector getInjectorInstance() {
        synchronized (INJECTOR_LOCK) {
            return INJECTOR;
        }
    }

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();
        super.contextInitialized(servletContextEvent);

        launchMetricsJmxReporter();
//        launchMetricsConsoleReporter();
    }

    @Override
    protected Injector getInjector() {
        synchronized (INJECTOR_LOCK) {
            if (INJECTOR != null) {
                return INJECTOR;
            }
            INJECTOR = Guice.createInjector(new Sws2Module(servletContext));
            return INJECTOR;
        }
    }

    private void launchMetricsJmxReporter() {
        JmxReporter reporter = getInjectorInstance().getInstance(JmxReporter.class);
        reporter.start();
    }

    private void launchMetricsConsoleReporter() {
        ConsoleReporter reporter = getInjectorInstance().getInstance(ConsoleReporter.class);
        reporter.start(1, TimeUnit.SECONDS);
    }
}
