package com.hzmc.weixin.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

public class DateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //操蛋的微信，为啥不是毫秒
        long time = date.getTime();
        jsonGenerator.writeString(String.valueOf(time).substring(0, 10));
    }
}
