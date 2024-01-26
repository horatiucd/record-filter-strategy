package com.hcd.recordfilterstrategy.listener;

import com.hcd.recordfilterstrategy.domain.Hero;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

public class HeroFilterStrategy implements RecordFilterStrategy<String, Hero> {

    private static final Logger LOG = LoggerFactory.getLogger(HeroFilterStrategy.class);

    @Override
    public boolean filter(ConsumerRecord<String, Hero> consumerRecord) {
        final Hero hero = consumerRecord.value();

        final boolean discard = hero.type() == Hero.CharacterType.EVIL;
        if (discard) {
            LOG.info("{} is not welcomed in our world.", hero);
        }
        return discard;
    }
}
