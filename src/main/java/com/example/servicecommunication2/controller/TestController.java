package com.example.servicecommunication2.controller;

import com.example.servicecommunication2.dto.LoginDTO;
import com.example.servicecommunication2.dto.UserDTO;
import com.example.servicecommunication2.service.ApiService;
import com.example.servicecommunication2.service.TestClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private TestClient testClient;

    @PostMapping("/test")
    public ResponseEntity<LoginDTO> login(@RequestBody UserDTO userDTO){
        var result = apiService.login(userDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/test-grpc")
    public ResponseEntity<Object> test(){
        testClient.callGrpc();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
