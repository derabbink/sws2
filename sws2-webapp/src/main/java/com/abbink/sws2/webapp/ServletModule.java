package com.abbink.sws2.webapp;

import com.google.inject.AbstractModule;

import javax.servlet.ServletContext;

public class ServletModule extends AbstractModule {
    private ServletContext servletContext;

    public ServletModule(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    protected void configure() {
        bind(ServletContext.class).toInstance(servletContext);
    }
}
