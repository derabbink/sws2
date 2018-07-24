package com.abbink.sws2.api.service.greeting;

import com.abbink.sws2.api.data.greeting.GreetingDto;

public class GreetingService {
    public GreetingDto getGreeting() {
        GreetingDto result = new GreetingDto();
        result.setGreeting("Hello World!");
        return result;
    }
}
