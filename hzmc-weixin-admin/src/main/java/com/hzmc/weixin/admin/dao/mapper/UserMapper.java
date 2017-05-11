package com.hzmc.weixin.admin.dao.mapper;

import com.hzmc.weixin.admin.dao.model.User;
import com.hzmc.weixin.admin.dao.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
	long countByExample(UserExample example);

	int deleteByExample(UserExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User getUserByNameAndPwd(User user);

	List<User> getUserByName(@Param("name") String name);
}