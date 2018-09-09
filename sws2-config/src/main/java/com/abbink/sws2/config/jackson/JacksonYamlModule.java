package com.abbink.sws2.config.jackson;

import com.abbink.sws2.common.jackson.Bindings;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class JacksonYamlModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(JsonFactory.class).annotatedWith(Bindings.ConfigurationYaml.class).toInstance(new YAMLFactory());
    }

    @Provides
    @Singleton
    @Bindings.ConfigurationYaml
    public ObjectMapper provideObjectMapper(@Bindings.ConfigurationYaml JsonFactory jsonFactory) {
        return new ObjectMapper(jsonFactory);
    }
}
