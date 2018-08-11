package com.abbink.sws2.common.di;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * An expansion of the regular {@link Module} interface that also declares a {@link #binder()} function.
 * It is recommended to use this on subtypes of {@link AbstractModule}, in order to get the {@link #binder()}
 * implementation (almost) for free.
 */
public interface ModuleWithBinder extends Module {
    /**
     * Inspired by {@link AbstractModule#binder}.
     * If your {@link ModuleWithBinder} extends from {@link AbstractModule}, simply make this
     * {@code return super.binder();}
     */
    Binder binder();
}
