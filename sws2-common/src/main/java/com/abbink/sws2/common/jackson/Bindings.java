package com.abbink.sws2.common.jackson;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This contains binding annotations to specify specific subtypes of {@code com.fasterxml.jackson.databind.ObjectMapper}
 * and {@code com.fasterxml.jackson.core.JsonFactory}.
 */
public final class Bindings {
    /** This should not be instantiated */
    private Bindings() {}

    @BindingAnnotation
    @Target({FIELD, METHOD, PARAMETER})
    @Retention(RUNTIME)
    public @interface ConfigurationYaml {
    }
}
