package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.cache.GlobalCache;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxRedpackTemplet;
import com.hzmc.weixin.admin.service.WxRedpackTempletService;
import com.hzmc.weixin.mp.oauth2.MpOAuth2s;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wph on 2017/5/15.
 */
@RestController
@RequestMapping(value = "/vote")
@Api(value = "投票控制器", description = "投票控制器管理")
public class VoteController extends BaseController {
	private static Logger LOGGER = Logger.getLogger(VoteController.class);

	@Autowired
	private WxRedpackTempletService wxRedpackTempletService;

	@RequestMapping(value = "/{id}/{openId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据openId和红包模板ID判断用户是否已经投票")
	private Object sendVote(@PathVariable Integer id, @PathVariable String openId) {
		User user = Users.defaultUsers().get(openId);
		if (user == null) {
			return new Result(ResultConstant.FAILED, "改用户不存在");
		}
		WxRedpackTemplet templet = wxRedpackTempletService.selectByPrimaryKey(id);
		if (templet == null) {
			return new Result(ResultConstant.FAILED, "红包模板不存在");
		}
		if (GlobalCache.CACHE_MAP.get(openId) == null) {
			GlobalCache.CACHE_MAP.put(openId, templet);
			return new Result(ResultConstant.SUCCESS, "投票成功");
		} else {
			return new Result(ResultConstant.FAILED, "已投票成功");
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	private Object url() {
		String url = MpOAuth2s.defaultOAuth2s().authenticationUrl("http://wx.mchz.com.cn/view_mobile/index.html", "snsapi_base");
		return url;
	}
}
