package com.hzmc.weixin.admin.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.User;
import com.hzmc.weixin.admin.service.UserService;
import com.hzmc.weixin.admin.util.MD5Util;
import com.hzmc.weixin.admin.util.validator.LengthValidator;
import com.hzmc.weixin.admin.util.validator.NotNullValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Usercontroller
 * Created by wph on 2017/4/24.
 */
@RestController
@RequestMapping("/user")
@Api(value = "系统用户控制器", description = "系统用户管理")
public class UserController {

	private static Logger _log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "添加系統用户")
	public Object addUser(@RequestBody User user) {
		ComplexResult result = FluentValidator.checkAll()
				.on(user.getUsername(), new LengthValidator(1, 10, "帐号"))
				.on(user.getPassword(), new LengthValidator(5, 32, "密码"))
				.on(user.getRealname(), new NotNullValidator("姓名"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new Result(ResultConstant.INVALID_LENGTH, result.getErrors());
		}
		long time = System.currentTimeMillis();
		String salt = UUID.randomUUID().toString().replaceAll("-", "");
		user.setSalt(salt);
		user.setPassword(MD5Util.MD5(user.getPassword() + user.getSalt()));
		user.setCtime(String.valueOf(time));
		List<User> userList = userService.getUserByName(user.getUsername());
		if (userList.size() > 0) {
			return new Result(ResultConstant.FAILED, "用户名已存在");
		}
		int count = userService.insert(user);
		_log.info("新增用户，主键：userId={}", user.getId());
		return new Result(ResultConstant.SUCCESS, count);
	}

}