package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxRedpackTemplet;
import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.service.AccessService;
import com.hzmc.weixin.admin.service.WxRedpackTempletService;
import com.hzmc.weixin.admin.service.WxUserService;
import com.hzmc.weixin.common.AccessToken;
import com.hzmc.weixin.mp.base.AppSetting;
import com.hzmc.weixin.mp.oauth2.MpOAuth2s;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wph on 2017/5/11.
 */
@RestController
@RequestMapping(value = "/oauth")
@Api(value = "网页授权控制器", description = "网页授权控制器")
public class OauthController {

	private static Logger LOGGER = Logger.getLogger(AccessService.class);

	@Autowired
	private WxRedpackTempletService wxRedpackTempletService;

	@Autowired
	private WxUserService userService;

	@RequestMapping(value = "/{openId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据openId判断用户是否关注")
	private Object getOAuthData(@PathVariable String openId) {
		WxRedpackTemplet wxRedpackTemplet = wxRedpackTempletService.selectByPrimaryKey(1);
		long curtime = System.currentTimeMillis() / 1000;
		long minTime = Long.valueOf(wxRedpackTemplet.getStartTime());
		long maxTime = Long.valueOf(wxRedpackTemplet.getEndTime());
		if (!(curtime >= minTime && curtime <= maxTime)) {
			return new Result(ResultConstant.FAILED, "活动已结束");
		}
		User user = Users.defaultUsers().get(openId);
		WxUser wxUser1 = userService.getWxUserByOpenId(openId);
		if (user.getGroup() == 102 || wxUser1.getGroupid() == 102) {
			return new Result(ResultConstant.FAILED, "内部人员不能发红包");
		}
		Map<String, Object> map = new HashedMap();
		if (user.isSubscribed()) {
			map.put("status", "已经关注");
			map.put("user", user);
			map.put("appId", AppSetting.defaultSettings().getAppId());
			return new Result(ResultConstant.SUCCESS, map);
		} else {
			map.put("status", "没有关注");
			map.put("appId", AppSetting.defaultSettings().getAppId());
			return new Result(ResultConstant.FAILED, map);
		}

	}

	@RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
	@ApiOperation(value = "根据code返回openId")
	private Object getOAuthCode(@PathVariable String code) {
		AccessToken token = MpOAuth2s.defaultOAuth2s().getAccessToken(code);
		return new Result(ResultConstant.SUCCESS, token.getOpenid());
	}
}
