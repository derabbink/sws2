package com.abbink.sws2.webapp;

import com.abbink.sws2.common.events.AppListener;
import com.abbink.sws2.common.events.Bindings;
import com.abbink.sws2.common.events.Bindings.AppListeners;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.jmx.JmxReporter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.GuiceServletContextListener;
import org.flywaydb.core.Flyway;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Sws2ServletContextListener extends GuiceServletContextListener {
    private static final Object INJECTOR_LOCK = new Object();
    private static Injector INJECTOR;

    public static Injector getInjectorInstance() {
        synchronized (INJECTOR_LOCK) {
            if (INJECTOR == null) {
                throw new IllegalStateException("The injector has not yet been initialized");
            }
            return INJECTOR;
        }
    }

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();
        super.contextInitialized(servletContextEvent);

        invokeAppStartListeners();
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

    private void invokeAppStartListeners() {
        Injector injector = getInjectorInstance();
        Set<AppListener> appListeners = injector.getInstance(
            Key.get(new TypeLiteral<Set<AppListener>>(){}, AppListeners.class)
        );
        for (AppListener appListener : appListeners) {
            appListener.appStarted();
        }
    }
}
