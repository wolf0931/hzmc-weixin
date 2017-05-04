package com.hzmc.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.hzmc.weixin.pay.payment.bean.UnifiedOrderRequest;

@JacksonXmlRootElement(localName = "xml")
public class UnifiedOrderRequestWrapper extends BaseSettings {

	@JsonUnwrapped
	private UnifiedOrderRequest request;

	public void setRequest(UnifiedOrderRequest request) {
		this.request = request;
	}

	public UnifiedOrderRequest getRequest() {
		return request;
	}
}