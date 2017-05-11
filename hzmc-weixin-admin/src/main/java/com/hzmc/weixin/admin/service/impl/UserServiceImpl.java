package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.BaseServiceImpl;
import com.hzmc.weixin.admin.dao.mapper.UserMapper;
import com.hzmc.weixin.admin.dao.model.User;
import com.hzmc.weixin.admin.dao.model.UserExample;
import com.hzmc.weixin.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService实现
 * Created by wph on 2017/4/24.
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {

	private static Logger _log = LoggerFactory.getLogger(UserServiceImpl.class);


	@Autowired
	private UserMapper mapper;

	@Override
	public User getUserByNameAndPwd(User user) {
		return mapper.getUserByNameAndPwd(user);
	}

	@Override
	public List<User> getUserByName(String name) {
		return mapper.getUserByName(name);
	}
}