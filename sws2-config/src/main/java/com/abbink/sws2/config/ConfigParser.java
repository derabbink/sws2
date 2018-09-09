package com.abbink.sws2.config;

import com.abbink.sws2.common.jackson.Bindings.ConfigurationYaml;
import com.abbink.sws2.config.dto.ConfigDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;

@Singleton
public class ConfigParser {
    private final ObjectMapper mapper;

    @Inject
    public ConfigParser(@ConfigurationYaml ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ConfigDto parse(InputStream yamlFile) throws IOException {
        return mapper.readValue(yamlFile, ConfigDto.class);
    }
}
