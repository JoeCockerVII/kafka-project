package com.producer.service;

import com.producer.configuration.ProducerConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    public void sendMessagePartition(String key, String message) {
        Random random = new Random();
        int topic = random.ints(0, ProducerConfiguration.PARTITIONS_TOPIC_NAME1).findFirst().getAsInt();
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(ProducerConfiguration.TOPIC_NAME1, topic, key, message);

        SendResult<String, String> result = future.get();

        if (result == null || result.getRecordMetadata() == null) {
            throw new RuntimeException("Cannot read record metadata");
        }
        log.info("Send message to: Topic -> {}, Offset -> {}, Partition -> {}, Message -> {}, Key -> {}",
                result.getRecordMetadata().topic(), result.getRecordMetadata().offset(), 
                result.getRecordMetadata().partition(),message,key
        );
    }

    @SneakyThrows
    public void sendMessageWithoutPartition(String key, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(ProducerConfiguration.TOPIC_NAME2, key, message);

        SendResult<String, String> result = future.get();

        if (result == null || result.getRecordMetadata() == null) {
            throw new RuntimeException("Cannot read record metadata");
        }
        log.info("Send message to: Topic -> {}, Offset -> {}, Partition -> {}, Message -> {}, Key -> {}",
                result.getRecordMetadata().topic(), result.getRecordMetadata().offset(),
                result.getRecordMetadata().partition(),message,key
        );
    }
}