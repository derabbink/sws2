package com.abbink.sws2.api.endpoints.greeting;

import com.abbink.sws2.api.data.greeting.GreetingDto;
import com.abbink.sws2.api.service.greeting.GreetingService;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static com.abbink.sws2.api.endpoints.greeting.GreetingEndpoint.PATH;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/greeting")
@Produces(APPLICATION_JSON)
public class GreetingEndpoint {
    public static final String PATH = "/greeting";

    private final GreetingService service;

    @Inject
    public GreetingEndpoint(GreetingService service) {
        this.service = service;
    }

    @GET
    // Using multiple instrumentation annotations requires explicit naming
    // Using explicit name suffixes overwrites the automatic use of the method name.
    @Metered(name = "get.Metered")
    @Timed(name = "get.Timed")
    @ExceptionMetered(name = "get.ExceptionMetered")
    public GreetingDto get() {
        return service.getGreeting();
    }
}
