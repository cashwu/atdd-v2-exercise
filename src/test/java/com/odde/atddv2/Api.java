package com.odde.atddv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leeonky.cucumber.restful.RestfulStep;
import com.odde.atddv2.entity.User;
import com.odde.atddv2.repo.UserRepo;
import io.cucumber.java.Before;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.leeonky.dal.Assertions.expect;

public class Api {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String response, token;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestfulStep restfulStep;

    @SneakyThrows
    @Before("@api-login")
    public void apiLogin() {
        User defaultUser = new User().setUserName("j").setPassword("j");
        userRepo.save(defaultUser);
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsString(defaultUser));
        Request request = new Request.Builder().url("http://localhost:10081/users/login").post(requestBody).build();
        token = okHttpClient.newCall(request).execute().header("token");
        restfulStep.header("token", token);
    }

    @SneakyThrows
    public void get(String path) {
        Request request = new Request.Builder()
                .url(String.format("http://localhost:10081/api/%s", path))
                .header("Accept", "application/json")
                .header("token", token)
                .get().build();

        response = okHttpClient.newCall(request).execute().body().string();
    }

    @SneakyThrows
    public void post(String path, Object body) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsString(body));
        Request request = new Request.Builder()
                .url(String.format("http://localhost:10081/api/%s", path))
                .header("Accept", "application/json")
                .header("token", token)
                .post(requestBody).build();
        response = okHttpClient.newCall(request).execute().body().string();
    }

    @SneakyThrows
    public void responseShouldMatchJson(String json) {
        JSONAssert.assertEquals(json, response, JSONCompareMode.NON_EXTENSIBLE);
    }

    public void responseShouldMatch(String expected) {
        expect(response).should(expected);
    }
}