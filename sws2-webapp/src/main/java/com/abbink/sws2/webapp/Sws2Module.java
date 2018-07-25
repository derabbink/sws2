package com.abbink.sws2.webapp;

import com.abbink.sws2.api.ApiModule;
import com.abbink.sws2.webapp.jersey.JerseyPluginsModule;
import com.google.inject.AbstractModule;

import javax.servlet.ServletContext;

public class Sws2Module extends AbstractModule { //ServletModule {
    private ServletContext servletContext;

    public Sws2Module(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    protected void configure() {
        bind(ServletContext.class).toInstance(servletContext);

        // install sws2 application dependencies
        install(new ApiModule());

        // configure jersey
        install(new JerseyPluginsModule());
    }
}
