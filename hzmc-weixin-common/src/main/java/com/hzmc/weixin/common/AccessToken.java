package com.hzmc.weixin.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hzmc.weixin.common.util.JsonMapper;

public class AccessToken {

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("expires_in")
	private long expiresIn;

	@JsonProperty("refresh_token")
	private String refresh_token;

	@JsonProperty("openid")
	private String openid;

	@JsonProperty("scope")
	private String scope;

	private long expiresTill;

	public static AccessToken fromJson(String json) {
		return JsonMapper.defaultMapper().fromJson(json, AccessToken.class);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
		this.expiresTill = System.currentTimeMillis() + (expiresIn * 1000) - 300000;
	}

	public long getExpiresTill() {
		return expiresTill;
	}

	public boolean expired() {
		return System.currentTimeMillis() > expiresTill;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setExpiresTill(long expiresTill) {
		this.expiresTill = expiresTill;
	}
}
