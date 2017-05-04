package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.dao.model.User;
import com.hzmc.weixin.admin.service.UserService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wph on 2017/4/25.
 */
@Controller
@Api(value = "登录控制器", description = "登录管理")
public class LoginController {
	private static Logger LOGGER = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestBody User user) {
		LOGGER.info(user.toString());
		return null;
	}

}
