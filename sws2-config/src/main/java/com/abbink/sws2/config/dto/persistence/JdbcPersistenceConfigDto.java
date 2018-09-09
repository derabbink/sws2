package com.abbink.sws2.config.dto.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JdbcPersistenceConfigDto {
    @JsonProperty("jdbc-uri")
    private String jdbcUri;

    public JdbcPersistenceConfigDto() {}

    public String getJdbcUri() {
        return jdbcUri;
    }
}
