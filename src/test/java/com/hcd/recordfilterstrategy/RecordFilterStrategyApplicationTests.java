package com.hcd.recordfilterstrategy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

@SpringBootTest
class RecordFilterStrategyApplicationTests {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.request}")
    private String topic;

    @Value("${context.id}")
    private String contextId;

    private static final String template = """
        {
            "id": "%s",
            "contextId": "%s"
        }""";

    @Test
    void compliant() {
        kafkaTemplate.send(topic,
                String.format(template, UUID.randomUUID(), contextId));
    }

    @Test
    void notCompliant() {
        kafkaTemplate.send(topic,
                String.format(template, UUID.randomUUID(), "other context"));
    }
}
