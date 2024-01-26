package com.hcd.recordfilterstrategy.listener;

import com.hcd.recordfilterstrategy.domain.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class HeroListener {

    private static final Logger LOG = LoggerFactory.getLogger(HeroListener.class);

    @KafkaListener(topics = "${topic.hero}", groupId = "${topic.hero.group.id}")
    public void onReceive(@Payload Hero hero) {
        LOG.info("A new hero was born - {}.", hero);
    }
}
