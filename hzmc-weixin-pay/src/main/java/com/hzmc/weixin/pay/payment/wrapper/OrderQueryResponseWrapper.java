package com.hzmc.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hzmc.weixin.pay.payment.bean.OrderQueryResponse;

public class OrderQueryResponseWrapper extends BaseSettings {

    @JsonUnwrapped
    private OrderQueryResponse response;

    public OrderQueryResponse getResponse() {
        return response;
    }

    public void setResponse(OrderQueryResponse response) {
        this.response = response;
    }
}