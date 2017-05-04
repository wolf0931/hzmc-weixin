package com.hzmc.weixin.common.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.hzmc.weixin.common.message.XmlMessageHeader;

public class EventRequest extends XmlMessageHeader {

    @JsonProperty("Event")
    @JacksonXmlCData
    private EventType eventType;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
