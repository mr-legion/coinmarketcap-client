package io.algostrategy.client.coinmarketcap.domain.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Custom deserializer for string. "Null" values are equivalent to the null type.
 */
public class StringDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        ObjectCodec oc = jp.getCodec();
        String value = oc.readValue(jp, String.class);
        return value.equalsIgnoreCase("null") ? null : value;
    }
}
