package com.abbink.sws2.webapp.config;

import com.abbink.sws2.common.config.Bindings.ConfigFile;
import com.abbink.sws2.common.config.persistence.Bindings.JdbcUri;
import com.abbink.sws2.common.io.InputStreamProvider;
import com.abbink.sws2.config.dto.ConfigDto;
import com.abbink.sws2.config.dto.persistence.PersistenceConfigDto;
import com.google.inject.AbstractModule;
import com.google.inject.throwingproviders.ThrowingProviderBinder;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.InputStream;

public class ConfigModule extends AbstractModule {
    public static final String CONFIG_FILE_OPTION = "sws2ConfigFile";
    public static final String DEFAULT_CONFIG_FILE_NAME = "/WEB-INF/default_config.yml";

    @Override
    protected void configure() {
        configureConfigFile();
        configurePersistence();
    }

    private void configureConfigFile() {
        requireBinding(ServletContext.class);

        // use a partially prepared injector to access the servlet context, and use that to bind configuration stuff
        ServletContext context = getProvider(ServletContext.class).get();
        final String filePath = findConfigFilePath(context);
        InputStreamProvider provider;
        if (filePath != null) {
            provider = () -> new FileInputStream(filePath);
        } else {
            // use default config file that is packaged with the application
            provider = () -> context.getResourceAsStream(DEFAULT_CONFIG_FILE_NAME);
        }
        ThrowingProviderBinder.create(binder())
            .bind(InputStreamProvider.class, InputStream.class)
            .annotatedWith(ConfigFile.class)
            .to(provider);

        install(new com.abbink.sws2.config.ConfigModule());
    }

    private String findConfigFilePath(ServletContext context) {
        String filePath = null;
        try {
            filePath = System.getProperty(CONFIG_FILE_OPTION);
        } catch (SecurityException e) {}
        if (filePath != null) {
            return filePath;
        }

        filePath = context.getInitParameter(CONFIG_FILE_OPTION);
        if (filePath != null) {
            return filePath;
        }
        return null;
    }

    private void configurePersistence() {
        requireBinding(ConfigDto.class);
        // use a partially prepared injector to access the config file, and use that to decide how to bind persistence
        ConfigDto config = getProvider(ConfigDto.class).get();

        if (config.getPersistence().getType() == PersistenceConfigDto.Type.JDBC) {
            configureJdbcPersistence(config);
        }
    }

    private void configureJdbcPersistence(ConfigDto config) {
        String jdbcUri = config.getPersistence().getJdbc().getJdbcUri();
        bind(String.class).annotatedWith(JdbcUri.class).toInstance(jdbcUri);
    }
}
