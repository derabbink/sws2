package com.abbink.sws2.common.events;

import com.abbink.sws2.common.di.AppListenerModule;
import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public final class Bindings {
    /** This should not be instantiated */
    private Bindings() {}

    /**
     * This is merely an additional restriction (beyond the type) on the {@code Class<AppListener>} multibinding that
     * will be assembled through {@link AppListenerModule}s.
     * Multibindings users will still be able to (multi)bind {@link AppListener} types for other purposes than
     * announcing them to any application lifecycle management code, by simply not doing so under the
     * {@link AppListeners} annotation.
     */
    @BindingAnnotation
    @Target({TYPE}) @Retention(RUNTIME)
    public @interface AppListeners {
    }
}
