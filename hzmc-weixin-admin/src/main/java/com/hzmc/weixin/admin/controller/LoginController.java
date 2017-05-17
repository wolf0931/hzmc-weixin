package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.User;
import com.hzmc.weixin.admin.service.UserService;
import com.hzmc.weixin.admin.util.MD5Util;
import com.hzmc.weixin.admin.util.UserSessionManger;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by wph on 2017/4/25.
 */
@RestController
@Api(value = "登录控制器", description = "登录管理")
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	private static Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Object login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info(user.toString());
		HttpSession session = request.getSession();
		LOGGER.info("session" + session);
		String username = user.getUsername();
		String password = user.getPassword();
		if (StringUtils.isBlank(username)) {
			return new Result(ResultConstant.EMPTY_USERNAME, "帐号不能为空！");
		}
		if (StringUtils.isBlank(password)) {
			return new Result(ResultConstant.EMPTY_PASSWORD, "密码不能为空！");
		}
		String salt = UUID.randomUUID().toString().replaceAll("-", "");
		user.setCtime(salt);
		user.setPassword(MD5Util.MD5(user.getPassword()));
		User user1 = userService.getUserByNameAndPwd(user);
		if (user1 == null) {
			return new Result(ResultConstant.FAILED, "用户不存在或密码不正确");
		} else {
			UserSessionManger.add(session.getId(), user);
			return new Result(ResultConstant.SUCCESS, user1);
		}
	}

	@RequestMapping(value = "/exit", method = RequestMethod.POST)
	public Object loginOut(HttpServletRequest request,
						   HttpServletResponse response) {
		String sessionId = request.getSession().getId();
		User user = UserSessionManger.get(sessionId);
		UserSessionManger.getUserInfoMap().remove(sessionId);
		return new Result(ResultConstant.SUCCESS, user);
	}


	/**
	 * .
	 * 获取登录信息
	 *
	 * @param request request
	 * @return Object
	 */
	@RequestMapping(value = "/account/info", method = RequestMethod.GET)
	public Object getLoginInfo(HttpServletRequest request) {
		User user = UserSessionManger.get(request.getSession().getId());
		if (user != null) {
			return new Result(ResultConstant.SUCCESS, user);
		} else {
			return new Result(ResultConstant.FAILED, "用户没有登录");
		}

	}
}
