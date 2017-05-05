package com.hzmc.weixin.admin.dao.mapper;

import com.hzmc.weixin.admin.dao.model.Group;
import com.hzmc.weixin.admin.dao.model.GroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
	long countByExample(GroupExample example);

	int deleteByExample(GroupExample example);

	int insert(Group record);

	int insertSelective(Group record);

	List<Group> selectByExample(GroupExample example);

	int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

	int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

	Group getGroupByName(@Param("name") String name);
}