package com.alevat.cabbage.account.rest;

import java.io.IOException;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class JsonHelper {

    @Inject
    private ObjectMapper objectMapper;

    String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Couldn't serialize as JSON " + object, e);
        }
    }

    <T> T fromJson(Class<T> type, String json) {
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new InvalidRequestException("Couldn't deserialize " + json + " as " + type.getName(), e);
        }
    }

}
