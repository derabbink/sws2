package com.abbink.sws2.api.data.greeting;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GreetingDto {

    @JsonProperty("greeting")
    private String greeting;

    /** Default constructor for jackson */
    public GreetingDto() {}

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
