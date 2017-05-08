package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.BaseServiceImpl;
import com.hzmc.weixin.admin.dao.mapper.WxGroupMapper;
import com.hzmc.weixin.admin.dao.model.WxGroup;
import com.hzmc.weixin.admin.dao.model.WxGroupExample;
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
public class GroupServiceImpl extends BaseServiceImpl<WxGroupMapper, WxGroup, WxGroupExample> implements GroupService {

	private static Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);

	@Autowired
	WxGroupMapper groupMapper;

	@Override
	public WxGroup getGroupByName(String name) {
		return groupMapper.getGroupByName(name);
	}
}