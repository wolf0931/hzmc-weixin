package com.hzmc.weixin.admin.dao.mapper;

import com.hzmc.weixin.admin.dao.model.WxPayRecord;
import com.hzmc.weixin.admin.dao.model.WxPayRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxPayRecordMapper {
    long countByExample(WxPayRecordExample example);

    int deleteByExample(WxPayRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxPayRecord record);

    int insertSelective(WxPayRecord record);

    List<WxPayRecord> selectByExample(WxPayRecordExample example);

    WxPayRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxPayRecord record, @Param("example") WxPayRecordExample example);

    int updateByExample(@Param("record") WxPayRecord record, @Param("example") WxPayRecordExample example);

    int updateByPrimaryKeySelective(WxPayRecord record);

    int updateByPrimaryKey(WxPayRecord record);

}