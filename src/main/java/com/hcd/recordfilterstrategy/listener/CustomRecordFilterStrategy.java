package com.hcd.recordfilterstrategy.listener;

import com.hcd.recordfilterstrategy.domain.Request;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Component
public class CustomRecordFilterStrategy implements RecordFilterStrategy<String, Request> {

    private final RequestFilterStrategy requestFilterStrategy;

    public CustomRecordFilterStrategy(RequestFilterStrategy requestFilterStrategy) {
        this.requestFilterStrategy = requestFilterStrategy;
    }

    @Override
    public boolean filter(ConsumerRecord<String, Request> consumerRecord) {
        return requestFilterStrategy.filter(consumerRecord.value().contextId());
    }
}
