/*
 * This file is generated by jOOQ.
 */
package com.abbink.sws2.persistence.generated.jooq.tables;


import com.abbink.sws2.persistence.generated.jooq.Keys;
import com.abbink.sws2.persistence.generated.jooq.Public;
import com.abbink.sws2.persistence.generated.jooq.tables.records.GreetingRecord;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Greeting extends TableImpl<GreetingRecord> {

    private static final long serialVersionUID = 402452715;

    /**
     * The reference instance of <code>PUBLIC.GREETING</code>
     */
    public static final Greeting GREETING = new Greeting();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GreetingRecord> getRecordType() {
        return GreetingRecord.class;
    }

    /**
     * The column <code>PUBLIC.GREETING.id</code>.
     */
    public final TableField<GreetingRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.GREETING.greeting</code>.
     */
    public final TableField<GreetingRecord, String> GREETING_ = createField("greeting", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.GREETING</code> table reference
     */
    public Greeting() {
        this(DSL.name("GREETING"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.GREETING</code> table reference
     */
    public Greeting(String alias) {
        this(DSL.name(alias), GREETING);
    }

    /**
     * Create an aliased <code>PUBLIC.GREETING</code> table reference
     */
    public Greeting(Name alias) {
        this(alias, GREETING);
    }

    private Greeting(Name alias, Table<GreetingRecord> aliased) {
        this(alias, aliased, null);
    }

    private Greeting(Name alias, Table<GreetingRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Greeting(Table<O> child, ForeignKey<O, GreetingRecord> key) {
        super(child, key, GREETING);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<GreetingRecord, Long> getIdentity() {
        return Keys.IDENTITY_GREETING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Greeting as(String alias) {
        return new Greeting(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Greeting as(Name alias) {
        return new Greeting(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Greeting rename(String name) {
        return new Greeting(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Greeting rename(Name name) {
        return new Greeting(name, null);
    }
}
