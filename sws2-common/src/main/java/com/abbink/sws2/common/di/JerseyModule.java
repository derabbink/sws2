package com.abbink.sws2.common.di;

import com.abbink.sws2.common.jersey.JerseyObjects;
import com.abbink.sws2.common.jersey.JerseyTypes;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import javax.inject.Inject;

public abstract class JerseyModule extends AbstractModule {

    /**
     * This lets you bind types to Guice-created instances, which will be registered with Jersey.
     * This differs from {@link #bindJerseyType(Class)} in that this passes pre-created instances to Jersey, instead of
     * letting Jersey manage that.
     * @param clazz Type reference that you want Guice to create an instance from. Must be {@link Inject}-able
     */
    protected void bindTypeAsJerseyObject(Class<?> clazz) {
        Multibinder<Class<?>> multibinder = Multibinder.newSetBinder(
            binder(),
            new TypeLiteral<Class<?>>(){},
            JerseyObjects.class
        );
        multibinder.addBinding().toInstance(clazz);
    }

    /**
     * This lets you bind types to their {@link Class} instances (not to providers or types), since only that way will
     * Jersey be able to pick up these {@link Class}es and register them with its own DI system. This way, the bound
     * {@link Class} types will be managed by Jersey (using HK2), but all non-Jersey dependencies will be fulfilled by
     * Guice.
     */
    protected void bindJerseyType(Class<?> clazz) {
        Multibinder<Class<?>> multibinder = Multibinder.newSetBinder(
            binder(),
            new TypeLiteral<Class<?>>(){},
            JerseyTypes.class
        );
        multibinder.addBinding().toInstance(clazz);
    }
}
