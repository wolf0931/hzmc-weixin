package com.hzmc.weixin.admin.service;

import com.hzmc.weixin.admin.base.BaseService;
import com.hzmc.weixin.admin.dao.model.WxGroup;
import com.hzmc.weixin.admin.dao.model.WxGroupExample;

/**
 * GroupService接口
 * Created by wph on 2017/5/4.
 */
public interface GroupService extends BaseService<WxGroup, WxGroupExample> {

	WxGroup getGroupByName(String name);
}