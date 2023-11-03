package com.example.xclientsSpringTests.api;

import com.example.xclientsSpringTests.model.UserInfo;

import java.io.IOException;

public interface XClientsAuth {
    UserInfo auth() throws IOException;
}