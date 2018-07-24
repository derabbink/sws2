package com.abbink.sws2.webapp.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes({"*/*"})
@Produces({"*/*"})
public class JacksonProviderWrapper extends JacksonJaxbJsonProvider {

    public JacksonProviderWrapper() {
        super();
        configureObjectMapper();
    }

    private void configureObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        setMapper(objectMapper);
    }
}
