package com.hcd.recordfilterstrategy.domain;

public record Hero (String id,
                    CharacterType type,
                    String name) {

    public Hero(String name) {
        this(null, null, name);
    }

    public enum CharacterType {
        GOOD, EVIL
    }
}
