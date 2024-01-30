package com.hcd.recordfilterstrategy.listener;

import com.hcd.recordfilterstrategy.domain.Request;
import com.hcd.recordfilterstrategy.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RequestMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(RequestMessageListener.class);

    private final ResponseService responseService;

    public RequestMessageListener(ResponseService responseService) {
        this.responseService = responseService;
    }

    @KafkaListener(topics = "${topic.request}", groupId = "${context.id}")
    public void onMessage(@Payload Request request) {
        LOG.info("Processing {}.", request);

        responseService.send(Response.success());
    }
}
