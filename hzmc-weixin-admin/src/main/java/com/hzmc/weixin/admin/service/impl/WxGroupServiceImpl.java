package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.BaseServiceImpl;
import com.hzmc.weixin.admin.dao.mapper.WxGroupMapper;
import com.hzmc.weixin.admin.dao.model.WxGroup;
import com.hzmc.weixin.admin.dao.model.WxGroupExample;
import com.hzmc.weixin.admin.service.WxGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* WxGroupService实现
* Created by wph on 2017/5/8.
*/
@Service
@Transactional
public class WxGroupServiceImpl extends BaseServiceImpl<WxGroupMapper, WxGroup, WxGroupExample> implements WxGroupService {

    private static Logger LOGGER = LoggerFactory.getLogger(WxGroupServiceImpl.class);

    @Autowired
    WxGroupMapper wxGroupMapper;

}