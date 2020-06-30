package com.javanorth.spring.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value(value = "${rabbitmqConfig.directQueue}")
    private String directQueue;

    @Value(value = "${rabbitmqConfig.directExchange}")
    private String directExchange;

    /**
     * 订阅者模式队列1
     */
    @Value(value = "${rabbitmqConfig.topicQueueOne}")
    private String topicQueueOne;

    /**
     * 发布订阅模式队列2
     */
    @Value(value = "${rabbitmqConfig.topicQueueTwo}")
    private String topicQueueTwo;

    /**
     * 发布订阅模式交换机
     */
    @Value(value = "${rabbitmqConfig.topicExchange}")
    private String topicExchange;

    /**
     * 发布订阅的路由1
     */
    @Value(value = "${rabbitmqConfig.topicRoutingKeyOne}")
    private String topicRoutingKeyOne;

    /**
     * 发布订阅路由2
     */
    @Value(value = "${rabbitmqConfig.topicRoutingKeyTwo}")
    private String topicRoutingKeyTwo;

    /**
     * 广播模式队列
     */
    @Value(value = "${rabbitmqConfig.fanoutQueueOne}")
    private String fanoutQueueOne;

    @Value(value = "${rabbitmqConfig.fanoutQueueTwo}")
    private String fanoutQueueTwo;

    /**
     * 广播模式交换机
     */
    @Value(value = "${rabbitmqConfig.fanoutExchange}")
    private String fanoutExchange;

    /**
     * 注册队列到spring-bean
     * @return direct模式队列
     */
    @Bean
    public Queue directQueue() {
        return new Queue(directQueue, true);
    }

    /**
     * 注册directExchange到spring-bean
     * @return directExchange交换机
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }

    /**
     * 注册订阅队列
     * @return 订阅队列
     */
    @Bean
    public Queue topicQueueOne() {
        return new Queue(topicQueueOne, true);
    }

    /**
     * 注册订阅队列
     * @return 订阅队列
     */
    @Bean
    public Queue topicQueueTwo() {
        return new Queue(topicQueueTwo, true);
    }

    /**
     * 注册发布订阅交换机
     * @return 发布订阅的交换机
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    /**
     * 路由和交换机绑定
     * @return
     */
    @Bean
    public Binding topicBindingOne() {
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(topicRoutingKeyOne);
    }

    /**
     * 队列和交换机绑定
     * @return
     */
    @Bean
    public Binding topicBindingTwo() {
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(topicRoutingKeyTwo);
    }

    /**
     * 广播模式队列1
     * @return 广播模式队列
     */
    @Bean
    public Queue fanoutQueueOne() {
        return new Queue(fanoutQueueOne, true);
    }

    /**
     * 创建广播队列2
     * @return
     */
    @Bean
    public Queue fanoutQueueTwo() {
        return new Queue(fanoutQueueTwo, true);
    }

    /**
     * 广播模式交换机
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    /**
     * 广播队列与交换机绑定
     * @return 绑定关系
     */
    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }


    /**
     * 广播队列与交换机绑定
     * @return 绑定关系
     */
    @Bean
    public Binding fanoutBindingTwo() {
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }



}
