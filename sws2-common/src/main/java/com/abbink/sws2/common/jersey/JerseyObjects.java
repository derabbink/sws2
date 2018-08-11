package com.abbink.sws2.common.jersey;

import com.abbink.sws2.common.di.JerseyModule;
import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This is merely an additional restriction (beyond the type) on the {@code Class<? extends IJerseyType>} multibinding
 * that will be assembled through {@link JerseyModule}s.
 * Multibindings users will still be able to (multi)bind {@code Class<?>} instances for other purposes than announcing
 * them to Jersey, by simply not doing so under the {@link JerseyObjects} annotation.
 */
@BindingAnnotation @Target({TYPE}) @Retention(RUNTIME)
public @interface JerseyObjects {
}
