package com.abbink.sws2.common.di;

import com.abbink.sws2.common.jersey.JerseyTypes;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

/**
 * This class only lets you bind types to their {@link Class} instances (not to providers or types), since only that way
 * will Jersey be able to pick up these {@link Class}es and register them with its own DI system.
 * This way, the bound {@link Class} types will be managed by Jersey (using HK2), but all non-Jersey dependencies will
 * be fulfilled by Guice.
 */
public abstract class JerseyTypesModule extends AbstractModule {

    protected void bindJerseyClass(Class<?> clazz) {
        Multibinder<Class<?>> multibinder = Multibinder.newSetBinder(
            binder(),
            new TypeLiteral<Class<?>>(){},
            JerseyTypes.class
        );
        multibinder.addBinding().toInstance(clazz);
    }
}
