package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.BaseServiceImpl;
import com.hzmc.weixin.admin.dao.mapper.TagMapper;
import com.hzmc.weixin.admin.dao.model.Tag;
import com.hzmc.weixin.admin.dao.model.TagExample;
import com.hzmc.weixin.admin.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* TagService实现
* Created by wph on 2017/5/4.
*/
@Service
@Transactional
public class TagServiceImpl extends BaseServiceImpl<TagMapper, Tag, TagExample> implements TagService {

    private static Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);

    @Autowired
    TagMapper tagMapper;

}