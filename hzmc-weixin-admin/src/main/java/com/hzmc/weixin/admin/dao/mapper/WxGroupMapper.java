package com.hzmc.weixin.admin.dao.mapper;

import com.hzmc.weixin.admin.dao.model.WxGroup;
import com.hzmc.weixin.admin.dao.model.WxGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxGroupMapper {
    long countByExample(WxGroupExample example);

    int deleteByExample(WxGroupExample example);

    int insert(WxGroup record);

    int insertSelective(WxGroup record);

    List<WxGroup> selectByExample(WxGroupExample example);

    int updateByExampleSelective(@Param("record") WxGroup record, @Param("example") WxGroupExample example);

    int updateByExample(@Param("record") WxGroup record, @Param("example") WxGroupExample example);

    WxGroup getGroupByName(@Param("name") String name);

}