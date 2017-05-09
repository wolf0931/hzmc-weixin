package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.BaseServiceImpl;
import com.hzmc.weixin.admin.dao.mapper.WxPayRecordMapper;
import com.hzmc.weixin.admin.dao.model.WxPayRecord;
import com.hzmc.weixin.admin.dao.model.WxPayRecordExample;
import com.hzmc.weixin.admin.service.WxPayRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* WxPayRecordService实现
* Created by wph on 2017/5/8.
*/
@Service
@Transactional
public class WxPayRecordServiceImpl extends BaseServiceImpl<WxPayRecordMapper, WxPayRecord, WxPayRecordExample> implements WxPayRecordService {

    private static Logger LOGGER = LoggerFactory.getLogger(WxPayRecordServiceImpl.class);

    @Autowired
    WxPayRecordMapper wxPayRecordMapper;

}