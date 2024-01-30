package com.hcd.recordfilterstrategy.listener;

import com.hcd.recordfilterstrategy.domain.Request;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Component
public class CustomRecordFilterStrategy implements RecordFilterStrategy<String, Request> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomRecordFilterStrategy.class);

    @Value("${context.id}")
    private String contextId;

    @Override
    public boolean filter(ConsumerRecord<String, Request> consumerRecord) {
        Request request = consumerRecord.value();

        boolean discard = !contextId.equals(request.contextId());
        LOG.info("{} is{} compliant.", request, discard ? "n't" : "");
        return discard;
    }
}
