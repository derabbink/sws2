package com.abbink.sws2.webapp.config;

import com.abbink.sws2.common.config.persistence.Bindings.JdbcUri;
import com.google.inject.AbstractModule;

public class ConfigModule extends AbstractModule {
    public static final String JDBC_URI = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    @Override
    protected void configure() {
        configurePersistence();
    }

    private void configurePersistence() {
        bind(String.class).annotatedWith(JdbcUri.class).toInstance(JDBC_URI);
    }
}
