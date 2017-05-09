package com.hzmc.weixin.admin.dao.mapper;

import com.hzmc.weixin.admin.dao.model.WxRedpackTemplet;
import com.hzmc.weixin.admin.dao.model.WxRedpackTempletExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxRedpackTempletMapper {
    long countByExample(WxRedpackTempletExample example);

    int deleteByExample(WxRedpackTempletExample example);

    int insert(WxRedpackTemplet record);

    int insertSelective(WxRedpackTemplet record);

    WxRedpackTemplet selectByPrimaryKey(Integer id);

    List<WxRedpackTemplet> selectByExample(WxRedpackTempletExample example);

    int updateByExampleSelective(@Param("record") WxRedpackTemplet record, @Param("example") WxRedpackTempletExample example);

    int updateByExample(@Param("record") WxRedpackTemplet record, @Param("example") WxRedpackTempletExample example);
}