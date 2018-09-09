package com.abbink.sws2.config;

import com.abbink.sws2.config.dto.ConfigDto;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.throwingproviders.CheckedProvider;

import java.io.IOException;

public interface MainConfigDtoProvider extends CheckedProvider<ConfigDto> {
    @Provides
    @Singleton
    ConfigDto get() throws IOException;
}
