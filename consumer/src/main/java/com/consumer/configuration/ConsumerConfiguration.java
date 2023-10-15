package com.consumer.configuration;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;

@Configuration
public class ConsumerConfiguration {

    public static final String TOPIC_NAME1 = "PARTITION";
    public static final String TOPIC_NAME2 = "WITHOUT_PARTITION";

    @Value("${spring.kafka.bootstrap-servers}")
    private String address;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        HashMap<String, Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
