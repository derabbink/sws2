package com.abbink.sws2.persistence;

import com.abbink.sws2.common.config.persistence.Bindings.JdbcUri;
import com.abbink.sws2.persistence.migration.MigrationModule;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provides;
import org.h2.jdbcx.JdbcDataSource;

import javax.inject.Singleton;
import javax.sql.DataSource;

public class PersistenceModule extends AbstractModule {
    @Override
    protected void configure() {
        requireBinding(Key.get(String.class, JdbcUri.class));

        install(new MigrationModule());
    }

    @Provides
    @Singleton
    public DataSource provideJdbcDataSource(@JdbcUri String jdbcUri) {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(jdbcUri);
        return ds;
    }
}
