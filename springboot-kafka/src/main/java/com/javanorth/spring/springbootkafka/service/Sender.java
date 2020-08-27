package com.javanorth.spring.springbootkafka.service;

import com.javanorth.spring.springbootkafka.pojo.Message;

public interface Sender {
    void sendMsg(Message msg);
}
