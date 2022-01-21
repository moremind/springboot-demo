package cn.moremind.spring.springbootkafka.config;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ToString
@Component
@PropertySource(value = "classpath:application.properties", encoding = "utf-8")
public class ProducerConfigurations {

    public static String BOOTSTRAP_SERVERS_CONFIG;
    public static int RETRIES_CONFIG;
    public static String ACK_CONFIG;
    public static int MAX_BLOCK_MS_CONFIG;
    public static int BATCH_SIZE_CONFIG;
    public static int LINGER_MS_CONFIG;
    public static int BUFFER_MEMORY_CONFIG;
    public static int MAX_REQUEST_SIZE_CONFIG;
    public static Class KEY_SERIALIZER_CLASS_CONFIG;
    public static Class VALUE_SERIALIZER_CLASS_CONFIG;
    public static String COMPRESSION_TYPE_CONFIG;

    public static String getBootstrapServersConfig() {
        return BOOTSTRAP_SERVERS_CONFIG;
    }

    @Value("${kafka.producer.config.bootstrap-servers}")
    public void setBootstrapServersConfig(String bootstrapServersConfig) {
        BOOTSTRAP_SERVERS_CONFIG = bootstrapServersConfig;
    }

    public static int getRetriesConfig() {
        return RETRIES_CONFIG;
    }

    @Value("${kafka.producer.config.retries}")
    public void setRetriesConfig(int retriesConfig) {
        RETRIES_CONFIG = retriesConfig;
    }

    public static String getAckConfig() {
        return ACK_CONFIG;
    }

    @Value("${kafka.producer.config.ack}")
    public void setAckConfig(String ackConfig) {
        ACK_CONFIG = ackConfig;
    }

    public static int getMaxBlockMsConfig() {
        return MAX_BLOCK_MS_CONFIG;
    }

    @Value("${kafka.producer.config.max-block-ms}")
    public void setMaxBlockMsConfig(int maxBlockMsConfig) {
        MAX_BLOCK_MS_CONFIG = maxBlockMsConfig;
    }

    public static int getBatchSizeConfig() {
        return BATCH_SIZE_CONFIG;
    }

    @Value("${kafka.producer.config.batch-size}")
    public void setBatchSizeConfig(int batchSizeConfig) {
        BATCH_SIZE_CONFIG = batchSizeConfig;
    }

    public static int getLingerMsConfig() {
        return LINGER_MS_CONFIG;
    }

    @Value("${kafka.producer.config.linger-ms}")
    public void setLingerMsConfig(int lingerMsConfig) {
        LINGER_MS_CONFIG = lingerMsConfig;
    }

    public static int getBufferMemoryConfig() {
        return BUFFER_MEMORY_CONFIG;
    }

    @Value("${kafka.producer.config.buffer-memory}")
    public void setBufferMemoryConfig(int bufferMemoryConfig) {
        BUFFER_MEMORY_CONFIG = bufferMemoryConfig;
    }

    public static int getMaxRequestSizeConfig() {
        return MAX_REQUEST_SIZE_CONFIG;
    }

    @Value("${kafka.producer.config.max-request-size}")
    public void setMaxRequestSizeConfig(int maxRequestSizeConfig) {
        MAX_REQUEST_SIZE_CONFIG = maxRequestSizeConfig;
    }

    public static Class getKeySerializerClassConfig() {
        return KEY_SERIALIZER_CLASS_CONFIG;
    }

    @Value("${kafka.producer.config.key-deserializer}")
    public void setKeySerializerClassConfig(Class keySerializerClassConfig) {
        KEY_SERIALIZER_CLASS_CONFIG = keySerializerClassConfig;
    }

    public static Class getValueSerializerClassConfig() {
        return VALUE_SERIALIZER_CLASS_CONFIG;
    }

    @Value("${kafka.producer.config.value-deserializer}")
    public void setValueSerializerClassConfig(Class valueSerializerClassConfig) {
        VALUE_SERIALIZER_CLASS_CONFIG = valueSerializerClassConfig;
    }

    public static String getCompressionTypeConfig() {
        return COMPRESSION_TYPE_CONFIG;
    }

    @Value("${kafka.producer.config.compression_type}")
    public void setCompressionTypeConfig(String compressionTypeConfig) {
        COMPRESSION_TYPE_CONFIG = compressionTypeConfig;
    }
}
