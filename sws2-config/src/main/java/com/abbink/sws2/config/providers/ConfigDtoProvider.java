package com.abbink.sws2.config.providers;

import com.abbink.sws2.config.dto.ConfigDto;
import com.google.inject.throwingproviders.CheckedProvider;

import java.io.IOException;

public interface ConfigDtoProvider extends CheckedProvider<ConfigDto> {
    @Override
    ConfigDto get() throws IOException;
}
