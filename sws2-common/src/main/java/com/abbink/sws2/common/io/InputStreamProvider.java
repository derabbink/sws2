package com.abbink.sws2.common.io;

import com.google.inject.throwingproviders.CheckedProvider;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamProvider extends CheckedProvider<InputStream> {
    @Override
    InputStream get() throws IOException;
}
