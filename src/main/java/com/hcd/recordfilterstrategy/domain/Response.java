package com.hcd.recordfilterstrategy.domain;

import java.util.UUID;

public record Response (String id,
                        Result result) {

    public static Response success() {
        return new Response(UUID.randomUUID().toString(), Result.SUCCESS);
    }

    public static Response failure() {
        return new Response(UUID.randomUUID().toString(), Result.FAILURE);
    }

    public enum Result {
        SUCCESS, FAILURE
    }
}
