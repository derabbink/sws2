package com.abbink.sws2.webapp;

import com.abbink.sws2.api.ApiModule;
import com.abbink.sws2.persistence.PersistenceModule;
import com.abbink.sws2.webapp.application.ApplicationModule;
import com.abbink.sws2.webapp.config.ConfigModule;
import com.abbink.sws2.webapp.jersey.JerseyPluginsModule;
import com.abbink.sws2.webapp.metrics.MetricsModule;
import com.google.inject.AbstractModule;

import javax.servlet.ServletContext;

public class Sws2Module extends AbstractModule {
    private ServletContext servletContext;

    public Sws2Module(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    protected void configure() {
        bind(ServletContext.class).toInstance(servletContext);

        // load application config
        install(new ConfigModule());
        // install common application components
        install(new PersistenceModule());
        install(new MetricsModule());
        install(new ApplicationModule());
        // configure jersey
        install(new JerseyPluginsModule());
        // install application behavior
        install(new ApiModule());



    }
}
