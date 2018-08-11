package com.abbink.sws2.persistence.migration;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public final class Bindings {
    /** This should not be instantiated */
    private Bindings() {}

    /**
     * This is a binding annotation for {@link String[]}s that describe locations for DB migration files (used by
     * Flyway)
     */
    @BindingAnnotation
    @Target({PARAMETER}) @Retention(RUNTIME)
    public @interface MigrationLocations {
    }
}
