package com.hzmc.weixin.admin.service;

import com.hzmc.weixin.admin.base.BaseService;
import com.hzmc.weixin.admin.dao.model.WxRedpackTemplet;
import com.hzmc.weixin.admin.dao.model.WxRedpackTempletExample;

import java.util.List;

/**
* WxRedpackTempletService接口
* Created by wph on 2017/5/8.
*/
public interface WxRedpackTempletService extends BaseService<WxRedpackTemplet, WxRedpackTempletExample> {
	List<WxRedpackTemplet> getTempletByActName(String actName);
}