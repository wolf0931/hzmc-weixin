package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.User;
import com.hzmc.weixin.admin.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wph on 2017/4/25.
 */
@RestController
@Api(value = "登录控制器", description = "登录管理")
public class LoginController extends BaseController {
	private static Logger LOGGER = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestBody User user) {
		LOGGER.info(user.toString());
		String username = user.getUsername();
		String password = user.getPassword();
		if (StringUtils.isBlank(username)) {
			return new Result(ResultConstant.EMPTY_USERNAME, "帐号不能为空！");
		}
		if (StringUtils.isBlank(password)) {
			return new Result(ResultConstant.EMPTY_PASSWORD, "密码不能为空！");
		}
		return null;
	}

}
