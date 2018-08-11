package com.abbink.sws2.common.di;

import com.abbink.sws2.common.events.AppListener;
import com.abbink.sws2.common.events.Bindings.AppListeners;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

/**
 * It is recommended to use this on subtypes of {@link AbstractModule}, in order to get the {@link #binder()}
 * implementation (almost) for free.
 */
public interface AppListenerModule extends ModuleWithBinder {

    default void bindAppListener(Class<? extends AppListener> appListenerType) {
        Multibinder<AppListener> multibinder = Multibinder.newSetBinder(
            binder(),
            new TypeLiteral<AppListener>(){},
            AppListeners.class
        );
        multibinder.addBinding().to(appListenerType);
    }
}
