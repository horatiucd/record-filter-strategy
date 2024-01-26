package com.hcd.recordfilterstrategy;

import com.hcd.recordfilterstrategy.domain.Hero;
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

    @Value("${topic.hero}")
    private String topic;

    private static final String template = "{" +
            "\"id\":\"%s\"," +
            "\"type\":\"%s\"," +
            "\"name\":\"%s\"" +
        "}";

    @Test
    void send_good_hero() {
        final String hero = String.format(template,
                UUID.randomUUID(), Hero.CharacterType.GOOD, "Spiderman");

        kafkaTemplate.send(topic, hero);
    }

    @Test
    void send_evil_hero() {
        final String hero = String.format(template,
                UUID.randomUUID(), Hero.CharacterType.EVIL, "Green Goblin");

        kafkaTemplate.send(topic, hero);
    }

}
