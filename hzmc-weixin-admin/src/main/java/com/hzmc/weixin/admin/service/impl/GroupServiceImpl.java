package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.BaseServiceImpl;
import com.hzmc.weixin.admin.dao.mapper.GroupMapper;
import com.hzmc.weixin.admin.dao.model.Group;
import com.hzmc.weixin.admin.dao.model.GroupExample;
import com.hzmc.weixin.admin.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * GroupService实现
 * Created by wph on 2017/5/4.
 */
@Service
@Transactional
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper, Group, GroupExample> implements GroupService {

	private static Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);

	@Autowired
	GroupMapper groupMapper;

	@Override
	public Group getGroupByName(String name) {
		return groupMapper.getGroupByName(name);
	}
}