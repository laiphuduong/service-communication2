package com.example.servicecommunication2.service;

import com.example.servicecommunication2.dto.LoginDTO;
import com.example.servicecommunication2.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@Service
public class ApiService {
    @Autowired
    private RestTemplate restTemplate;

    private LoginClient loginClient;

    public LoginDTO login(UserDTO userDTO) {
//        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO);
//
//        ResponseEntity<LoginDTO> loginDTO = restTemplate.postForObject("http://localhost:8085/login", request, ResponseEntity.class);

        Feign.Builder builder = Feign.builder();
        builder.client(new OkHttpClient());
        builder.encoder(new GsonEncoder());
        builder.decoder(new GsonDecoder());
        builder.logger(new Slf4jLogger(ApiService.class));
        builder.logLevel(Logger.Level.FULL);
        this.loginClient = builder
                .target(LoginClient.class, "http://localhost:8085/login");
        var result = loginClient.login(userDTO);
        return result.getBody();
    }
}
