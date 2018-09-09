package com.abbink.sws2.config.dto.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersistenceConfigDto {
    @JsonProperty("type")
    private Type type;

    @JsonProperty("jdbc")
    private JdbcPersistenceConfigDto jdbc;

    public PersistenceConfigDto() {}

    public Type getType() {
        return type;
    }

    public JdbcPersistenceConfigDto getJdbc() {
        return jdbc;
    }

    public enum Type {
        JDBC,
    }
}
