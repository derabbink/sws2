package com.abbink.sws2.persistence.schema;

import com.abbink.sws2.persistence.JooqMigration;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.impl.SQLDataType;

import javax.persistence.Column;
import java.sql.Connection;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class V2__Greeting extends JooqMigration {
    public static final Table<?> TBL_GREETING = table("greeting");
    public static final Field<Long> COL_ID = field(name("id"), SQLDataType.BIGINT.nullable(false).identity(true));
    public static final Field<String> COL_GREETING = field(name("greeting"), SQLDataType.VARCHAR(255).nullable(false));

    @Override
    public void migrate(Connection connection) throws Exception {
        DSLContext dsl = dsl(connection);
        dsl.createTable(TBL_GREETING)
            .column(COL_ID)
            .column(COL_GREETING)
            .execute();
        dsl.insertInto(TBL_GREETING)
            .columns(COL_GREETING)
            .values("Hello World")
            .execute();
    }
}
