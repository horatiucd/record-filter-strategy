package com.hcd.recordfilterstrategy.domain;

public record Request(String id,
                      String contextId,
                      Type type) {

    public enum Type {
        JOKE, POEM, STORY, SONG
    }
}
