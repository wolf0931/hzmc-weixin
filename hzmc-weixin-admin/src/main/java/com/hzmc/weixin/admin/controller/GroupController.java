package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.mp.user.Groups;
import com.hzmc.weixin.mp.user.bean.Group;
import io.swagger.annotations.Api;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by wph on 2017/4/26.
 */
@RestController
@Api(value = "分组控制器", description = "微信公众号分组管理")
@RequestMapping("/group")
public class GroupController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object getGroups() {
		List<Group> list = Groups.defaultGroups().list();
		Map<String,Object> result = new HashedMap();
		result.put("groups",list);
		return new Result(ResultConstant.SUCCESS, result);
	}

}
