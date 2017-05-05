package com.hzmc.weixin.admin.service;

import com.hzmc.weixin.admin.base.BaseService;
import com.hzmc.weixin.admin.dao.model.Group;
import com.hzmc.weixin.admin.dao.model.GroupExample;

/**
 * GroupService接口
 * Created by wph on 2017/5/4.
 */
public interface GroupService extends BaseService<Group, GroupExample> {

	Group getGroupByName(String name);
}