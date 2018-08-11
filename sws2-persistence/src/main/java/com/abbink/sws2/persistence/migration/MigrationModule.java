package com.abbink.sws2.persistence.migration;

import com.abbink.sws2.persistence.migration.Bindings.MigrationLocations;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class MigrationModule extends AbstractModule {
    public static final String MIGRATION_LOCATIONS[] = new String[]{"classpath:com.abbink.sws2.persistence.schema"};

    @Override
    protected void configure() {
        requireBinding(DataSource.class);
        // requireBinding(Key.get(String.class, MigrationLocations.class));
        bind(String[].class).annotatedWith(MigrationLocations.class).toInstance(MIGRATION_LOCATIONS);
    }

    @Provides
    public Flyway provideFlyway(DataSource ds, @MigrationLocations String[] locations) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setLocations(locations);
        return flyway;
    }
}
