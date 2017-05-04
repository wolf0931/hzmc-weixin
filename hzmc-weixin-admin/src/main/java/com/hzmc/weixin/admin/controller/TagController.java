package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.mp.user.Tags;
import com.hzmc.weixin.mp.user.bean.Tag;
import com.hzmc.weixin.mp.user.bean.UserPagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by wph on 2017/4/26.
 */
@RestController
@Api(value = "标签控制器", description = "微信公众号标签管理")
@RequestMapping("/tag")
public class TagController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "得到所有的标签")
	public Object getTags() {
		List<Tag> list = Tags.defaultTags().list();
		Map<String, Object> result = new HashedMap();
		result.put("Tags", list);
		return new Result(ResultConstant.SUCCESS, result);
	}

	@RequestMapping(value = "/{tagId}", method = RequestMethod.GET)
	@ApiOperation(value = "得到标签下的用户")
	public Object getTagUser(@PathVariable int tagId) {
		UserPagination userPagination = Tags.defaultTags().listUsers(tagId);
		return userPagination;
	}
}
