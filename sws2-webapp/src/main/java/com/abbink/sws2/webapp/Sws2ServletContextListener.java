package com.abbink.sws2.webapp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class Sws2ServletContextListener extends GuiceServletContextListener {
    private static final Object INJECTOR_LOCK = new Object();
    private static Injector INJECTOR;

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();
        super.contextInitialized(servletContextEvent);
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

    public static Injector getInjectorInstance() {
        synchronized (INJECTOR_LOCK) {
            return INJECTOR;
        }
    }
}
