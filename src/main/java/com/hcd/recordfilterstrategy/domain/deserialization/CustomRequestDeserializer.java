package com.hcd.recordfilterstrategy.domain.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.hcd.recordfilterstrategy.domain.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomRequestDeserializer extends StdDeserializer<Request> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomRequestDeserializer.class);

    public CustomRequestDeserializer() {
        super(Request.class);
    }

    @Override
    public Request deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);

        final String contextId = deserializeField(root, "contextId");

        final String id = deserializeField(root, "id");
        if (id == null || id.isEmpty()) {
            throw new RequestDeserializationException("'id' is required");
        }

        Request request = new Request(id, contextId);
        LOG.info("Successfully deserialized {}", request);
        return request;
    }

    private static String deserializeField(JsonNode node, String field) {
        if (node.has(field)) {
            return node.get(field).asText();
        }
        return null;
    }
}
