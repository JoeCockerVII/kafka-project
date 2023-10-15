package com.consumer.service;

import com.consumer.configuration.ConsumerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = ConsumerConfiguration.TOPIC_NAME1)
    public void getMessage1(String msg,
                            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        log.info("----- Message from Topic: {} Message:{} Key: {} ----- ", ConsumerConfiguration.TOPIC_NAME1, msg, key);
    }


    @KafkaListener(topics = ConsumerConfiguration.TOPIC_NAME2)
    public void getMessage2(String msg,
                            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        log.info("----- Message from Topic: {} Message:{} Key: {} ----- ", ConsumerConfiguration.TOPIC_NAME2, msg, key);
    }


}