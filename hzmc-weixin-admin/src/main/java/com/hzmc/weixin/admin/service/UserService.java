package com.hzmc.weixin.admin.service;

import com.hzmc.weixin.admin.base.BaseService;
import com.hzmc.weixin.admin.dao.model.User;
import com.hzmc.weixin.admin.dao.model.UserExample;

import javax.servlet.http.HttpServletRequest;

/**
* UserService接口
* Created by wph on 2017/4/24.
*/
public interface UserService extends BaseService<User, UserExample> {

	boolean isLogin(HttpServletRequest request);

}