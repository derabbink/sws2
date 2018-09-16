package com.abbink.sws2.common.config.providers;

import com.google.inject.throwingproviders.CheckedProvider;

public interface ConfigBasedProvider<T> extends CheckedProvider<T> {
    @Override
    T get() throws Exception;

    class Exception extends java.lang.Exception {
        public Exception(String message) {
            super(message);
        }
        public Exception(Throwable cause) {
            super(cause);
        }
    }
}
