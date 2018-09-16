package com.abbink.sws2.config;

import com.abbink.sws2.common.config.Bindings.ConfigFile;
import com.abbink.sws2.common.io.InputStreamProvider;
import com.abbink.sws2.config.dto.ConfigDto;
import com.abbink.sws2.config.jackson.JacksonYamlModule;
import com.abbink.sws2.config.providers.ConfigDtoProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;

import java.io.IOException;
import java.io.InputStream;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        requireBinding(Key.get(InputStreamProvider.class, ConfigFile.class));

        // create & install a module that uses the @CheckedProvides methods
        install(ThrowingProviderBinder.forModule(this));
        install(new JacksonYamlModule());
        bind(ConfigParser.class);
    }

    @CheckedProvides(ConfigDtoProvider.class)
    @Singleton
    public ConfigDto provideMainConfigDto(
        ConfigParser parser,
        @ConfigFile InputStreamProvider yamlFileProvider
    ) throws IOException {
        InputStream yamlFile = yamlFileProvider.get();
        return parser.parse(yamlFile);
    }
}
