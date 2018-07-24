package com.abbink.sws2.api.endpoints.greeting;

import com.abbink.sws2.api.data.greeting.GreetingDto;
import com.abbink.sws2.api.service.greeting.GreetingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/greeting")
@Produces(APPLICATION_JSON)
public class GreetingEndpoint {

    private final GreetingService service;

    @Inject
    public GreetingEndpoint(GreetingService service) {
        this.service = service;
    }

    @GET
    public GreetingDto get() {
        return service.getGreeting();
    }
}
