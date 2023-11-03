package com.example.servicecommunication2.service;

import com.example.awsecs.proto.HelloRequest;
import com.example.awsecs.proto.HelloResponse;
import com.example.awsecs.proto.HelloServiceGrpc;

import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class TestClient {
    public void callGrpc() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Baeldung")
                .setLastName("gRPC")
                .build());

        channel.shutdown();
    }

}
