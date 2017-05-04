package com.hzmc.weixin.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hzmc.weixin.pay.util.DateDeserializer;

import java.util.Date;

/**
 * Created by wph on 2017/4/24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedPackReport {

	@JsonProperty("openid")
	private String openId;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("rcv_time")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date rcv_time;


	public RedPackReport() {
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getRcv_time() {
		return rcv_time;
	}

	public void setRcv_time(Date rcv_time) {
		this.rcv_time = rcv_time;
	}

	@Override
	public String toString() {
		return "RedPackReport{" +
				"openId='" + openId + '\'' +
				", amount=" + amount +
				", rcv_time=" + rcv_time +
				'}';
	}
}
