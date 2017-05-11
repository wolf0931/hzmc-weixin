package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.service.WxUserService;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.UserPagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
	private WxUserService wxuserService;

	@RequestMapping(value = "/{curPage}/{curSize}", method = RequestMethod.GET)
	@ApiOperation(value = "获取微信公众号关注的用户列表")
	public Object getUserList(@PathVariable Integer curPage, @PathVariable Integer curSize) {
		List<com.hzmc.weixin.mp.user.bean.User> userList = new ArrayList<>();
		UserPagination userPagination = Users.defaultUsers().list();
		int count = userPagination.getCount();
		int total = userPagination.getTotal();
		List<String> users = userPagination.getUsers();
		int startnum = (curPage * curSize) - curSize;
		int endNum = curPage * curSize;
		for (int i = startnum; i < endNum; i++) {
			com.hzmc.weixin.mp.user.bean.User user = Users.defaultUsers().get(users.get(i));
			userList.add(user);
		}
		Map<String, Object> result = new HashedMap();
		result.put("users", userList);
		return new Result(ResultConstant.SUCCESS, result);
	}

	@RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
	private Object getWxUserList(@PathVariable String groupId) {
		List<WxUser> wxUserList = wxuserService.getWxUserListByGroupId(groupId);
		Map<String, Object> map = new HashedMap();
		map.put("users", wxUserList);
		return new Result(ResultConstant.SUCCESS, map);
	}
}