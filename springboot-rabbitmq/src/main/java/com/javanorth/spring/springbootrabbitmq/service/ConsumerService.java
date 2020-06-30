package com.javanorth.spring.springbootrabbitmq.service;

public interface ConsumerService {
    void onMessage(String msg);
}
