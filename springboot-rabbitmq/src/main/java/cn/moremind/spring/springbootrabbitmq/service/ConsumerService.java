package cn.moremind.spring.springbootrabbitmq.service;

public interface ConsumerService {
    void onMessage(String msg);
}
