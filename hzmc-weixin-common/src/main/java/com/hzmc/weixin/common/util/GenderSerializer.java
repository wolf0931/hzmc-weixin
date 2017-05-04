package com.hzmc.weixin.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hzmc.weixin.common.user.Gender;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<Gender> {

    @Override
    public void serialize(Gender gender, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(gender.getCode());
    }
}
