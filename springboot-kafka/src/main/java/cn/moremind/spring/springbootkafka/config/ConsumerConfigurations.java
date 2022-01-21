package cn.moremind.spring.springbootkafka.config;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ToString
@Component
@PropertySource(value = "classpath:application.properties", encoding = "utf-8")
public class ConsumerConfigurations {

    public static String BOOTSTRAP_SERVERS_CONFIG;
    public static boolean ENABLE_AUTO_COMMIT_CONFIG;
    public static String AUTO_COMMIT_INTERVAL_MS_CONFIG;
    public static String SESSION_TIMEOUT_MS_CONFIG;
    public static Class KEY_DESERIALIZER_CLASS_CONFIG;
    public static Class VALUE_DESERIALIZER_CLASS_CONFIG;
    public static String AUTO_OFFSET_RESET_CONFIG;
    public static String HEART_BEAT;
    public static int CONSUMER_THREAD;
    public static int POLLING_TIMEOUT;

    public static String getBootstrapServersConfig() {
        return BOOTSTRAP_SERVERS_CONFIG;
    }

    @Value("${kafka.producer.config.bootstrap-servers}")
    public void setBootstrapServersConfig(String bootstrapServersConfig) {
        BOOTSTRAP_SERVERS_CONFIG = bootstrapServersConfig;
    }

    public static boolean isEnableAutoCommitConfig() {
        return ENABLE_AUTO_COMMIT_CONFIG;
    }

    @Value("${kafka.consumer.config.enable-auto-commit}")
    public void setEnableAutoCommitConfig(boolean enableAutoCommitConfig) {
        ENABLE_AUTO_COMMIT_CONFIG = enableAutoCommitConfig;
    }

    public static String getAutoCommitIntervalMsConfig() {
        return AUTO_COMMIT_INTERVAL_MS_CONFIG;
    }

    @Value("${kafka.consumer.auto-commit-interval-ms}")
    public void setAutoCommitIntervalMsConfig(String autoCommitIntervalMsConfig) {
        AUTO_COMMIT_INTERVAL_MS_CONFIG = autoCommitIntervalMsConfig;
    }

    public static String getSessionTimeoutMsConfig() {
        return SESSION_TIMEOUT_MS_CONFIG;
    }

    @Value("${kafka.consumer.session-timeout-ms}")
    public void setSessionTimeoutMsConfig(String sessionTimeoutMsConfig) {
        SESSION_TIMEOUT_MS_CONFIG = sessionTimeoutMsConfig;
    }

    public static Class getKeyDeserializerClassConfig() {
        return KEY_DESERIALIZER_CLASS_CONFIG;
    }

    @Value("${kafka.consumer.config.key-deserializer}")
    public void setKeyDeserializerClassConfig(Class keyDeserializerClassConfig) {
        KEY_DESERIALIZER_CLASS_CONFIG = keyDeserializerClassConfig;
    }

    public static Class getValueDeserializerClassConfig() {
        return VALUE_DESERIALIZER_CLASS_CONFIG;
    }

    @Value("${kafka.consumer.config.value-deserializer}")
    public void setValueDeserializerClassConfig(Class valueDeserializerClassConfig) {
        VALUE_DESERIALIZER_CLASS_CONFIG = valueDeserializerClassConfig;
    }

    public static String getAutoOffsetResetConfig() {
        return AUTO_OFFSET_RESET_CONFIG;
    }

    @Value("${kafka.consumer.config.auto-offset-reset}")
    public void setAutoOffsetResetConfig(String autoOffsetResetConfig) {
        AUTO_OFFSET_RESET_CONFIG = autoOffsetResetConfig;
    }

    public static String getHeartBeat() {
        return HEART_BEAT;
    }

    @Value("${kafka.consumer.config.heartbeat-interval}")
    public void setHeartBeat(String heartBeat) {
        HEART_BEAT = heartBeat;
    }

    public static int getConsumerThread() {
        return CONSUMER_THREAD;
    }

    @Value("${kafka.consumer.config.concurrency-thread}")
    public void setConsumerThread(int consumerThread) {
        CONSUMER_THREAD = consumerThread;
    }

    public static int getPollingTimeout() {
        return POLLING_TIMEOUT;
    }

    @Value("${kafka.consumer.config.polling-timeout}")
    public void setPollingTimeout(int pollingTimeout) {
        POLLING_TIMEOUT = pollingTimeout;
    }
}
