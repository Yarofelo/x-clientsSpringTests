package com.example.xclientsSpringTests.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private ObjectMapper mapper;

    public Mapper() {
        mapper = new ObjectMapper();
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}