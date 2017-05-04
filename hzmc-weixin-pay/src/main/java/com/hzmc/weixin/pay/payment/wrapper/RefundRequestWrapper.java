package com.hzmc.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hzmc.weixin.pay.payment.bean.RefundRequest;

public class RefundRequestWrapper extends BaseSettings {

    @JsonUnwrapped
    private RefundRequest request;

    public RefundRequest getRequest() {
        return request;
    }

    public void setRequest(RefundRequest request) {
        this.request = request;
    }
}
