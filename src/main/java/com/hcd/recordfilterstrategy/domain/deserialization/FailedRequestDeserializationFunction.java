package com.hcd.recordfilterstrategy.domain.deserialization;

import com.hcd.recordfilterstrategy.domain.Request;
import com.hcd.recordfilterstrategy.domain.Response;
import com.hcd.recordfilterstrategy.listener.RequestFilterStrategy;
import com.hcd.recordfilterstrategy.listener.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FailedRequestDeserializationFunction implements Function<FailedDeserializationInfo, Request> {

    private static final Logger LOG = LoggerFactory.getLogger(FailedRequestDeserializationFunction.class);

    private final RequestFilterStrategy requestFilterStrategy;
    private final ResponseService responseService;

    public FailedRequestDeserializationFunction(RequestFilterStrategy requestFilterStrategy,
                                                ResponseService responseService) {
        this.requestFilterStrategy = requestFilterStrategy;
        this.responseService = responseService;
    }

    @Override
    public Request apply(FailedDeserializationInfo failedDeserializationInfo) {
        final Exception ex = failedDeserializationInfo.getException();

        if (ex instanceof RequestDeserializationException deserializationEx) {
            LOG.info("Error deserializing request - {}", deserializationEx.getMessage());

            if (!requestFilterStrategy.filter(deserializationEx.getContextId())) {
                responseService.send(Response.failure());
            }
        } else {
            LOG.error("Unexpected error deserializing request.", ex);
        }

        return null;
    }
}
