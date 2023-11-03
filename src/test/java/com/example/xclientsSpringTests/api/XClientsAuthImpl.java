package com.example.xclientsSpringTests.api;

import com.example.xclientsSpringTests.model.UserInfo;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XClientsAuthImpl implements XClientsAuth {
    @Value("${base_url}")
    private String BASE_URL;
    @Value("${auth_path1}")
    private String AUTH_PATH_1;
    @Value("${auth_path2}")
    private String AUTH_PATH_2;
    @Value("${username_admin}")
    private String username_admin;
    @Value("${password}")
    private String password;
    @Autowired
    private XClientOkHttp client;
    @Autowired
    private Mapper mapper;
    private MediaType APPLICATION_JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    public UserInfo auth() throws IOException {
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder().addPathSegment(AUTH_PATH_1).addPathSegment(AUTH_PATH_2).build();
        RequestBody body = RequestBody
                .create("{\"username\":\"" + username_admin + "\",\"password\":\"" + password + "\"}", APPLICATION_JSON);
        Request request = new Request.Builder().post(body).url(url).build();
        Response response = client.getClient().newCall(request).execute();
        UserInfo info = mapper.getMapper().readValue(response.body().string(), UserInfo.class);
        return info;
    }
}