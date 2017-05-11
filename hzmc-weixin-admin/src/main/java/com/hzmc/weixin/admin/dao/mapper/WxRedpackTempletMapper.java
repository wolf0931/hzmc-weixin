package com.hzmc.weixin.admin.dao.mapper;

import com.hzmc.weixin.admin.dao.model.WxRedpackTemplet;
import com.hzmc.weixin.admin.dao.model.WxRedpackTempletExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxRedpackTempletMapper {
    long countByExample(WxRedpackTempletExample example);

    int deleteByExample(WxRedpackTempletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxRedpackTemplet record);

    int insertSelective(WxRedpackTemplet record);

    List<WxRedpackTemplet> selectByExample(WxRedpackTempletExample example);

    WxRedpackTemplet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxRedpackTemplet record, @Param("example") WxRedpackTempletExample example);

    int updateByExample(@Param("record") WxRedpackTemplet record, @Param("example") WxRedpackTempletExample example);

    int updateByPrimaryKeySelective(WxRedpackTemplet record);

    int updateByPrimaryKey(WxRedpackTemplet record);

    List<WxRedpackTemplet> getTempletByActName(@Param("actName") String actName);
}