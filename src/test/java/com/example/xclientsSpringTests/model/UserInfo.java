package com.example.xclientsSpringTests.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    private String userToken;

    public String getUserToken() {
        return userToken;
    }
}