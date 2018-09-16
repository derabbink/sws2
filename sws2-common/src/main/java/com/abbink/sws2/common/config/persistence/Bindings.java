package com.abbink.sws2.common.config.persistence;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public final class Bindings {
    /** This should not be instantiated */
    private Bindings() {}

    /**
     * This is a binding annotation for {@link String}s that describe a JDBC connection string
     */
    @BindingAnnotation
    @Target({METHOD, PARAMETER}) @Retention(RUNTIME)
    public @interface JdbcUri {
    }
}
