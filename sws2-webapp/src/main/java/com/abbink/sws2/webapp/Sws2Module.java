package com.abbink.sws2.webapp;

import com.abbink.sws2.api.ApiModule;
import com.abbink.sws2.webapp.jersey.JerseyPluginsModule;
import com.abbink.sws2.webapp.jersey.JerseyServlet;
import com.abbink.sws2.webapp.jersey.ResourceConfigProvider;
import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.AnnotationAcceptingListener;
import org.glassfish.jersey.server.internal.scanning.ResourceProcessor;

import javax.servlet.ServletContext;

public class Sws2Module extends AbstractModule { //ServletModule {
    private ServletContext servletContext;

    public Sws2Module(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
//    protected void configureServlets() {
    protected void configure() {
        install(new JerseyPluginsModule());
        // install sws2 application dependencies
        install(new ApiModule());

        // Reuse JerseyTypes's default resource scanning/identification mechanism, such that our ResourceConfigProvider
        // can use that to sift through all our Guice-bound JerseyTypes types (resources/providers/etc)
        bind(ResourceProcessor.class)
            .toInstance(AnnotationAcceptingListener.newJaxrsResourceAndProviderListener(getClass().getClassLoader()));

        // the following is inspired by Inspired by https://github.com/jersey/jersey/pull/133/
        // Provide ResourceConfig via Guice, otherwise JerseyTypes will wrap Application into ResourceConfig itself and we
        // will not be able to hook into the initialization procedure
        bind(ResourceConfig.class).toProvider(ResourceConfigProvider.class);

//        serve("/*").with(JerseyServlet.class);
    }
}
