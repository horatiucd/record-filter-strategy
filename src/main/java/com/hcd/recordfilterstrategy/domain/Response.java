package com.hcd.recordfilterstrategy.domain;

import java.util.UUID;

public record Response (String id,
                        String requestId,
                        Result result) {

    public static Response successFor(String requestId) {
        return new Response(UUID.randomUUID().toString(), requestId, Result.SUCCESS);
    }

    public static Response failureFor(String requestId) {
        return new Response(UUID.randomUUID().toString(), requestId, Result.FAILURE);
    }

    public enum Result {
        SUCCESS, FAILURE
    }
}
