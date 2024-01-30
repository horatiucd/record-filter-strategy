package com.hcd.recordfilterstrategy.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hcd.recordfilterstrategy.domain.deserialization.CustomRequestDeserializer;

@JsonDeserialize(using = CustomRequestDeserializer.class)
public record Request(String id,
                      String contextId) {
}
