package com.hzmc.weixin.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Created by wph on 2017/4/24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedPackResultWrapper {

	@JsonProperty("Red_name")
	private String name;
	@JsonUnwrapped
	private RedPackResult redPackResult;

	@JsonUnwrapped
	private AppSettingMixin appSettingMixin;

	public RedPackResultWrapper() {
	}

	public RedPackResult getRedPackResult() {
		return redPackResult;
	}

	public void setRedPackResult(RedPackResult redPackResult) {
		this.redPackResult = redPackResult;
	}

	public AppSettingMixin getAppSettingMixin() {
		return appSettingMixin;
	}

	public void setAppSettingMixin(AppSettingMixin appSettingMixin) {
		this.appSettingMixin = appSettingMixin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RedPackResultWrapper{" +
				"name='" + name + '\'' +
				", redPackResult=" + redPackResult +
				", appSettingMixin=" + appSettingMixin +
				'}';
	}
}
