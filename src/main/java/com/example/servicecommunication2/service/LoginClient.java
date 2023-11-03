package com.example.servicecommunication2.service;

import com.example.servicecommunication2.dto.LoginDTO;
import com.example.servicecommunication2.dto.UserDTO;

import org.springframework.http.ResponseEntity;

import feign.Headers;
import feign.RequestLine;

//@FeignClient(value="aws-ecs")
public interface LoginClient {
    @RequestLine("POST")
    @Headers(value = "Content-Type: application/json")
    ResponseEntity<LoginDTO> login(UserDTO userDTO);


    //producer consumer
}
