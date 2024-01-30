package com.hcd.recordfilterstrategy.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RequestFilterStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(RequestFilterStrategy.class);

    @Value("${context.id}")
    private String contextId;

    public boolean filter(String contextId) {
        boolean discard = !this.contextId.equals(contextId);
        LOG.info("Request is{} compliant.", discard ? "n't" : "");
        return discard;
    }
}
