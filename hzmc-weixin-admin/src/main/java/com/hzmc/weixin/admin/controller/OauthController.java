package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.service.AccessService;
import com.hzmc.weixin.common.AccessToken;
import com.hzmc.weixin.mp.oauth2.MpOAuth2s;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wph on 2017/5/11.
 */
@RestController
@RequestMapping(value = "/oauth")
@Api(value = "网页授权控制器", description = "网页授权控制器")
public class OauthController {

	private static Logger LOGGER = Logger.getLogger(AccessService.class);

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ApiOperation(value = "根据code判断用户是否关注")
	private Object getOAuthDate(@PathVariable String code) {
		LOGGER.info(code);
		AccessToken token = MpOAuth2s.defaultOAuth2s().getAccessToken(code);
		String openId = token.getOpenid();
		User user = Users.defaultUsers().get(openId);
		if (user.isSubscribed()) {
			return new Result(ResultConstant.SUCCESS, "已经关注");
		} else {
			return new Result(ResultConstant.FAILED, "没有关注");
		}

	}
}
