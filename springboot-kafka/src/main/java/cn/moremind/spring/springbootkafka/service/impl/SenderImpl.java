package cn.moremind.spring.springbootkafka.service.impl;

import com.google.gson.Gson;
import cn.moremind.spring.springbootkafka.pojo.Message;
import cn.moremind.spring.springbootkafka.service.Sender;
import cn.moremind.spring.springbootkafka.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Service
public class SenderImpl implements Sender {

    @Value("${kafka.topic.name}")
    String sendTopicName;


    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMsg(Message msg) {
        Gson gson = new Gson();

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(sendTopicName, gson.toJson(msg));

        // get result asynchronously
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                // failed
                LogUtil.error(SenderImpl.class, "send message failed");
            }

            @Override
            public void onSuccess(SendResult<String, String> stringMessageSendResult) {
                // success
                LogUtil.info(SenderImpl.class, "send message success");
            }
        });

        // get result synchronously
        try {
            SendResult<String, String> result = future.get();
            LogUtil.info(SenderImpl.class, "send message success:=== {}", result.toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
