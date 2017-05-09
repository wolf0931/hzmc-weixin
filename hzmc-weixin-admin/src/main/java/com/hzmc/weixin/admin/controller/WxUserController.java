package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.service.WxUserService;
import io.swagger.annotations.Api;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * WxUsercontroller
 * Created by wph on 2017/5/4.
 */
@RestController
@RequestMapping("/WxUser")
@Api(value = "微信用户控制器", description = "微信用户管理")
public class WxUserController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(WxUserController.class);

	@Autowired
	private WxUserService userService;

	@RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
	private Object getWxUserList(@PathVariable String groupId) {
		List<WxUser> wxUserList = userService.getWxUserListByGroupId(groupId);
		Map<String, Object> map = new HashedMap();
		map.put("users", wxUserList);
		return new Result(ResultConstant.SUCCESS, map);
	}


}