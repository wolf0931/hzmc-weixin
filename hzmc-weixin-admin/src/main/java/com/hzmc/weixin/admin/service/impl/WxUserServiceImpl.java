package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.BaseServiceImpl;
import com.hzmc.weixin.admin.dao.mapper.WxUserMapper;
import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.dao.model.WxUserExample;
import com.hzmc.weixin.admin.service.WxUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * WxUserService实现
 * Created by wph on 2017/5/4.
 */
@Service
@Transactional
public class WxUserServiceImpl extends BaseServiceImpl<WxUserMapper, WxUser, WxUserExample> implements WxUserService {

	private static Logger LOGGER = LoggerFactory.getLogger(WxUserServiceImpl.class);

	@Autowired
	WxUserMapper wxUserMapper;

	@Override
	public WxUser getWxUserByOpenId(String openId) {

		return wxUserMapper.getWxUserByOpenId(openId);
	}

	@Override
	public int updateWxUserByOpenId(WxUser wxUser) {
		return wxUserMapper.updateWxUserByOpenId(wxUser);
	}

	@Override
	public List<WxUser> getWxUserListByGroupId(String groupId) {
		return wxUserMapper.getWxUserListByGroupId(groupId);
	}
}