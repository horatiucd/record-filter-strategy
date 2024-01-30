package com.hcd.recordfilterstrategy.listener;

import com.hcd.recordfilterstrategy.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseService.class);

    public void send(Response response) {
        LOG.info("Sending {}.", response);
    }
}
