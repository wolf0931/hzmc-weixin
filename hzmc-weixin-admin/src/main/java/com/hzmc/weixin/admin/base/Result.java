package com.hzmc.weixin.admin.base;

import com.hzmc.weixin.admin.constant.ResultConstant;

/**
 * Created by wph on 2017/4/25.
 */
public class Result extends BaseResult {

	public Result(ResultConstant ResultConstant, Object data) {
		super(ResultConstant.getCode(), ResultConstant.getMessage(), data);
	}

}
