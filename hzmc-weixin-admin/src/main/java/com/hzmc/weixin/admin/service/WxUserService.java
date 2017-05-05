package com.hzmc.weixin.admin.service;

import com.hzmc.weixin.admin.base.BaseService;
import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.dao.model.WxUserExample;

import java.util.List;

/**
 * WxUserService接口
 * Created by wph on 2017/5/4.
 */
public interface WxUserService extends BaseService<WxUser, WxUserExample> {

	WxUser getWxUserByOpenId(String openId);

	List<WxUser> getWxUserListByGroupId(String groupId);

}