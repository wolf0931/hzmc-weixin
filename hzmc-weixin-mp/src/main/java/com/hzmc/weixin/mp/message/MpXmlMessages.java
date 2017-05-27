package com.hzmc.weixin.mp.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hzmc.weixin.common.event.EventRequest;
import com.hzmc.weixin.common.exception.WxRuntimeException;
import com.hzmc.weixin.common.message.XmlMessageHeader;
import com.hzmc.weixin.common.request.TextRequest;
import com.hzmc.weixin.common.util.XmlObjectMapper;
import com.hzmc.weixin.mp.event.ticket.SceneSubEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MpXmlMessages {

    private static Logger logger = LoggerFactory.getLogger(MpXmlMessages.class);

    public static XmlMessageHeader fromXml(String xml) {
        try {
            XmlMessageHeader xmlRequest = XmlObjectMapper.defaultMapper().fromXml(xml, XmlMessageHeader.class);
            switch (xmlRequest.getMsgType()) {
                case text:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, TextRequest.class);
                case event:
                    return toEvent(xml);
                default:
                    logger.warn("xml to bean failed, unknown message type {}.", xmlRequest.getMsgType());
                    throw new WxRuntimeException(999, "xml to bean failed, unknown message type " + xmlRequest.getMsgType());
            }
        } catch (IOException e) {
            logger.error("xml to message request failed", e);
            throw new WxRuntimeException(999, "xml to message request failed," + e.getMessage());
        }
    }

    private static EventRequest toEvent(String xml) {
        try {
            EventRequest eventRequest = XmlObjectMapper.defaultMapper().fromXml(xml, EventRequest.class);
            System.out.println("事件类型"+eventRequest.getEventType());
            switch (eventRequest.getEventType()) {
                case subscribe:
                case unsubscribe:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, SceneSubEvent.class);
                default:
                    logger.warn("xml to event, unknown event type {}.", eventRequest.getEventType());
                    throw new WxRuntimeException(999, "xml to bean event, unknown event type " + eventRequest.getEventType());
            }
        } catch (IOException e) {
            logger.error("xml to event failed", e);
            throw new WxRuntimeException(999, "xml to event failed," + e.getMessage());
        }
    }

    public static String toXml(XmlMessageHeader xmlMessage) {
        try {
            return XmlObjectMapper.defaultMapper().toXml(xmlMessage);
        } catch (JsonProcessingException e) {
            logger.error("message to xml failed", e);
            throw new WxRuntimeException(999, "message to xml failed," + e.getMessage());
        }
    }
}
