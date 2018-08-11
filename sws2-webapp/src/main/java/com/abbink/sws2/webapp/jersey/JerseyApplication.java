package com.abbink.sws2.webapp.jersey;

import com.abbink.sws2.common.di.JerseyModule;
import com.abbink.sws2.common.jersey.JerseyObjects;
import com.abbink.sws2.common.jersey.JerseyTypes;
import com.abbink.sws2.webapp.Sws2ServletContextListener;
import com.google.inject.Binding;
import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import java.util.Set;

import static com.google.inject.spi.Elements.getElements;

/**
 * This sets up the Guice-to-HK2 bridge (unidirectional only).
 * This also dynamically registers all {@link JerseyTypes}/{@link JerseyObjects}-annotated resources & providers that
 * are bound through {@link JerseyModule}s.
 *
 * NOTE: This can only be instantiated after the {@link Sws2ServletContextListener} was initialized.
 */
public class JerseyApplication extends ResourceConfig {
    /**
     * This gets injected by JerseyTypes/HK2, so the Guice injector has to be obtained in
     * {@link #createUnidirectionalGuiceBridge}
     */
    @Inject
    public JerseyApplication(ServiceLocator hk2ServiceLocator) {
        super();
        Injector guiceInjector = createUnidirectionalGuiceBridge(hk2ServiceLocator);
        registerJerseyObjectsFromGuice(guiceInjector);
        registerJerseyClassesFromGuice(guiceInjector);
    }

    private Injector createUnidirectionalGuiceBridge(ServiceLocator hk2ServiceLocator) {
        Injector guiceInjector = Sws2ServletContextListener.getInjectorInstance();
        if (guiceInjector == null) {
            throw new IllegalStateException("No Guice Injector could be found.");
        }
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(hk2ServiceLocator);
        GuiceIntoHK2Bridge g2h = hk2ServiceLocator.getService(GuiceIntoHK2Bridge.class);
        g2h.bridgeGuiceInjector(guiceInjector);
        return guiceInjector;
    }

    private void registerJerseyObjectsFromGuice(Injector guiceInjector) {
        try {
            Set<Class<?>> jerseyObjectClasses = guiceInjector.getInstance(
                Key.get(new TypeLiteral<Set<Class<?>>>() {}, JerseyObjects.class)
            );
            for (Class jerseyObjectClass : jerseyObjectClasses) {
                Object jerseyObject = guiceInjector.getInstance(jerseyObjectClass);
                register(jerseyObject);
            }
        } catch (ConfigurationException e) {
            // Don't do anything. This can happen if no jersey objects were bound
        }
    }

    private void registerJerseyClassesFromGuice(Injector guiceInjector) {
        try {
            Set<Class<?>> jerseyClasses = guiceInjector.getInstance(
                Key.get(new TypeLiteral<Set<Class<?>>>(){}, JerseyTypes.class)
            );
            registerClasses(jerseyClasses);
        } catch (ConfigurationException e) {
            // Don't do anything. This can happen if no jersey types were bound
        }
    }
}
