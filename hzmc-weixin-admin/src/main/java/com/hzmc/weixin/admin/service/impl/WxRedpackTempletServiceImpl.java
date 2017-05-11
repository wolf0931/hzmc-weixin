package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.BaseServiceImpl;
import com.hzmc.weixin.admin.dao.mapper.WxRedpackTempletMapper;
import com.hzmc.weixin.admin.dao.model.WxRedpackTemplet;
import com.hzmc.weixin.admin.dao.model.WxRedpackTempletExample;
import com.hzmc.weixin.admin.service.WxRedpackTempletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * WxRedpackTempletService实现
 * Created by wph on 2017/5/8.
 */
@Service
@Transactional
public class WxRedpackTempletServiceImpl extends BaseServiceImpl<WxRedpackTempletMapper, WxRedpackTemplet, WxRedpackTempletExample> implements WxRedpackTempletService {

	private static Logger LOGGER = LoggerFactory.getLogger(WxRedpackTempletServiceImpl.class);

	@Autowired
	WxRedpackTempletMapper wxRedpackTempletMapper;

	@Override
	public List<WxRedpackTemplet> getTempletByActName(String actName) {
		return wxRedpackTempletMapper.getTempletByActName(actName);
	}
}