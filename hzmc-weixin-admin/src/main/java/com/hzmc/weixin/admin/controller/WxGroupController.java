package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxGroup;
import com.hzmc.weixin.admin.dao.model.WxGroupExample;
import com.hzmc.weixin.admin.service.GroupService;
import io.swagger.annotations.Api;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
* WxGroupcontroller
* Created by wph on 2017/5/8.
*/
@RestController
@RequestMapping("/WxGroup")
@Api(value = "微信分组控制器", description = "微信分组管理")
public class WxGroupController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(WxGroupController.class);


    @Autowired
    private GroupService groupService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object getGroups() {
        WxGroupExample groupExample = new WxGroupExample();
        List<WxGroup> list = groupService.selectByExample(groupExample);
        Map<String, Object> result = new HashedMap();
        result.put("groups", list);
        return new Result(ResultConstant.SUCCESS, result);
    }
}