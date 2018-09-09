package com.abbink.sws2.webapp.application;

import com.abbink.sws2.common.events.AppListener;
import org.flywaydb.core.Flyway;

import javax.inject.Inject;

public class DbMigrationAppListener implements AppListener {

    private final Flyway migrator;

    @Inject
    public DbMigrationAppListener(Flyway migrator) {
        this.migrator = migrator;
    }

    @Override
    public void appStarted() {
        migrator.migrate();
    }
}
