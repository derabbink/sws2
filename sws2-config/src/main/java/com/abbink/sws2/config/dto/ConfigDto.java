package com.abbink.sws2.config.dto;

import com.abbink.sws2.config.dto.persistence.PersistenceConfigDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigDto {
    @JsonProperty("persistence")
    private PersistenceConfigDto persistence;

    public ConfigDto() {}

    public PersistenceConfigDto getPersistence() {
        return persistence;
    }
}
