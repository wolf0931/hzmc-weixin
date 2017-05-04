package com.hzmc.weixin.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wph on 2017/4/24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppSettingMixin {

	@JsonProperty("mch_id")
	private String mchId;

	@JsonProperty("nonce_str")
	private String nonce;

	private String sign;

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "AppSettingMixin{" +
				"mchId='" + mchId + '\'' +
				", nonce='" + nonce + '\'' +
				", sign='" + sign + '\'' +
				'}';
	}
}
