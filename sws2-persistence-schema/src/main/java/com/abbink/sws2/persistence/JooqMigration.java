package com.abbink.sws2.persistence;

import org.flywaydb.core.api.configuration.ConfigurationAware;
import org.flywaydb.core.api.configuration.FlywayConfiguration;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.JDBCUtils;

import java.sql.Connection;

public abstract class JooqMigration implements JdbcMigration, ConfigurationAware {

    private FlywayConfiguration flywayConfiguration;

    protected DSLContext dsl(Connection connection) {
        return DSL.using(connection, dialect(connection));
    }

    /**
     * TODO find a way to specify the dialect in the flyway config available via {@link #getFlywayConfiguration()}
     */
    protected SQLDialect dialect(Connection connection) {
        // inspired by {@link DSL#using(Connection, SQLDialect}
        return JDBCUtils.dialect(connection);
    }

    public FlywayConfiguration getFlywayConfiguration() {
        return flywayConfiguration;
    }

    @Override
    public void setFlywayConfiguration(FlywayConfiguration flywayConfiguration) {
        this.flywayConfiguration = flywayConfiguration;
    }
}
