package com.quattrogatti.effective.transaction.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.quattrogatti.effective.transaction.domain.Amount;

import java.io.IOException;

public class AmountSerializer extends JsonSerializer<Amount> {
    @Override
    public void serialize(Amount amount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeNumber(amount.toDouble());
    }
}
