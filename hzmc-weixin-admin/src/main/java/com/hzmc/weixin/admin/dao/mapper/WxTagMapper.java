package com.hzmc.weixin.admin.dao.mapper;

import com.hzmc.weixin.admin.dao.model.WxTag;
import com.hzmc.weixin.admin.dao.model.WxTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxTagMapper {
    long countByExample(WxTagExample example);

    int deleteByExample(WxTagExample example);

    int insert(WxTag record);

    int insertSelective(WxTag record);

    List<WxTag> selectByExample(WxTagExample example);

    int updateByExampleSelective(@Param("record") WxTag record, @Param("example") WxTagExample example);

    int updateByExample(@Param("record") WxTag record, @Param("example") WxTagExample example);
}