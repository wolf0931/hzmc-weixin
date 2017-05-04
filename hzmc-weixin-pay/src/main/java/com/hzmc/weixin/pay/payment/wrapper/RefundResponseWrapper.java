package com.hzmc.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hzmc.weixin.pay.payment.bean.RefundResponse;

public class RefundResponseWrapper extends BaseSettings {

    @JsonUnwrapped
    private RefundResponse response;

    public RefundResponse getResponse() {
        return response;
    }

    public void setResponse(RefundResponse response) {
        this.response = response;
    }
}
