package com.abbink.sws2.webapp.config;

import com.abbink.sws2.common.config.Bindings.ConfigFile;
import com.abbink.sws2.common.config.persistence.Bindings.JdbcUri;
import com.abbink.sws2.common.io.InputStreamProvider;
import com.abbink.sws2.config.dto.ConfigDto;
import com.abbink.sws2.config.dto.persistence.PersistenceConfigDto;
import com.abbink.sws2.common.config.providers.ConfigBasedProvider;
import com.abbink.sws2.config.providers.ConfigDtoProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;

import javax.annotation.Nullable;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigModule extends AbstractModule {
    public static final String CONFIG_FILE_OPTION = "sws2ConfigFile";
    public static final String DEFAULT_CONFIG_FILE_NAME = "/WEB-INF/default_config.yml";

    @Override
    protected void configure() {
        install(new ConfigDependencyModule());
        install(new com.abbink.sws2.config.ConfigModule());
        install(new PersistenceConfigDependencyModule());
    }

    public static class ConfigDependencyModule extends AbstractModule {
        @Override
        protected void configure() {
            requireBinding(ServletContext.class);

            // create & install a module that uses the @CheckedProvides methods
            install(ThrowingProviderBinder.forModule(this));
        }

        @Provides
        @ConfigFile
        @Singleton
        public String provideConfigFilePath(ServletContext context) {
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

        @CheckedProvides(InputStreamProvider.class)
        @ConfigFile
        public InputStream provideInputStream(
            @ConfigFile @Nullable String configFilePath,
            ServletContext servletContext
        ) throws IOException {
            if (configFilePath != null) {
                return new FileInputStream(configFilePath);
            } else {
                // use default config file that is packaged with the application
                return servletContext.getResourceAsStream(DEFAULT_CONFIG_FILE_NAME);
            }
        }

    }

    public static class PersistenceConfigDependencyModule extends AbstractModule {
        @Override
        protected void configure() {
            requireBinding(ConfigDtoProvider.class);

            // create & install a module that uses the @CheckedProvides methods
            install(ThrowingProviderBinder.forModule(this));
        }

        @CheckedProvides(ConfigBasedProvider.class)
        @Singleton
        @JdbcUri
        private String provideJdbcUri(ConfigDtoProvider configDtoProvider) throws ConfigBasedProvider.Exception {
            ConfigDto config;
            try {
                config = configDtoProvider.get();
            } catch (IOException e) {
                throw new ConfigBasedProvider.Exception(e);
            }
            if (config.getPersistence().getType() != PersistenceConfigDto.Type.JDBC) {
                throw new ConfigBasedProvider.Exception("Persistence not configured for JDBC");
            }
            return config.getPersistence().getJdbc().getJdbcUri();
        }
    }
}
