package com.abbink.sws2.config.dto.persistence;

import com.abbink.sws2.config.ConfigParser;
import com.abbink.sws2.config.dto.ConfigDto;
import com.abbink.sws2.config.jackson.JacksonYamlModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersistenceConfigDtoTest {
    private static final String TEST_FILE = "PersistenceConfigDtoTest.yml";

    private ConfigParser parser;

    @Before
    public void before() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                install(new JacksonYamlModule());
                bind(ConfigParser.class);
            }
        });
        parser = injector.getInstance(ConfigParser.class);
    }

    @Test
    public void testPropertiesGetParsed() throws IOException {
        ConfigDto dto = null;
        try (InputStream testFile = getClass().getResourceAsStream(TEST_FILE)) {
            dto = parser.parse(testFile);
        }

        PersistenceConfigDto persistenceDto = dto.getPersistence();
        assertThat(persistenceDto.getType(), equalTo(PersistenceConfigDto.Type.JDBC));
    }
}
