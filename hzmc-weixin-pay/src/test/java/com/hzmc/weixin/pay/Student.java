package com.hzmc.weixin.pay;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wph on 2017/4/24.
 */
public class Student {

	@JsonProperty("name")
	private String name;
	@JsonProperty("sex")
	private int sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", sex=" + sex +
				'}';
	}

	public void setSex(int sex) {
		this.sex = sex;
	}


}
