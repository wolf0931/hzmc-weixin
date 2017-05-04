package com.hzmc.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hzmc.weixin.pay.base.BaseResponse;

public class OrderCloseResponseWrapper extends BaseSettings {

    @JsonUnwrapped
    private BaseResponse response;

    public BaseResponse getResponse() {
        return response;
    }

    public void setResponse(BaseResponse response) {
        this.response = response;
    }
}
