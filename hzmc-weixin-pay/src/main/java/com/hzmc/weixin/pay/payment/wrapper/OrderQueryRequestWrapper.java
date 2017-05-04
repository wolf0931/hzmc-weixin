package com.hzmc.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.hzmc.weixin.pay.payment.bean.OrderQueryRequest;

@JacksonXmlRootElement(localName = "xml")
public class OrderQueryRequestWrapper extends BaseSettings {

    @JsonUnwrapped
    private OrderQueryRequest request;

    public void setRequest(OrderQueryRequest request) {
        this.request = request;
    }

    public OrderQueryRequest getRequest() {
        return request;
    }
}
