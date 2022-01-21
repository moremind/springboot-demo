package cn.moremind.spring.springbootkafka.service;

import cn.moremind.spring.springbootkafka.pojo.Message;

public interface Sender {
    void sendMsg(Message msg);
}
