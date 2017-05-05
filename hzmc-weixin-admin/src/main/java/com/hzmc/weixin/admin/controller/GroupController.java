package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.Group;
import com.hzmc.weixin.admin.dao.model.GroupExample;
import com.hzmc.weixin.admin.service.GroupService;
import io.swagger.annotations.Api;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private GroupService groupService;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object getGroups() {
		GroupExample groupExample = new GroupExample();
		List<Group> list = groupService.selectByExample(groupExample);
		Map<String, Object> result = new HashedMap();
		result.put("groups", list);
		return new Result(ResultConstant.SUCCESS, result);
	}

}
