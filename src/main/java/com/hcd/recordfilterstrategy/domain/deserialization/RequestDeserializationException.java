package com.hcd.recordfilterstrategy.domain.deserialization;

public class RequestDeserializationException extends RuntimeException {

    private final String contextId;

    public RequestDeserializationException(String contextId, String message) {
        super(message);
        this.contextId = contextId;
    }

    public String getContextId() {
        return contextId;
    }
}
