package com.abbink.sws2.common.config;

import com.abbink.sws2.common.io.InputStreamProvider;
import com.google.inject.BindingAnnotation;

import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class Bindings {
    /** This should not be instantiated */
    private Bindings() {}

    /**
     * This is a binding annotation for {@link String}s, {@link InputStream}s and {@link InputStreamProvider}s that
     * describe a config file URI
     */
    @BindingAnnotation
    @Target({METHOD, PARAMETER}) @Retention(RUNTIME)
    public @interface ConfigFile {
    }
}
