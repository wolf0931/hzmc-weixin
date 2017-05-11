package com.hzmc.weixin.admin;

import com.hzmc.weixin.common.util.JsonMapper;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.User;
import org.junit.Test;

/**
 * Created by wph on 2017/4/26.
 */
public class UserTest {

	@Test
	public void testGet() {
		User user = Users.defaultUsers().get("olgszxLvaC4QwLwKABb42ZJlXXo0");
		System.out.println(JsonMapper.defaultMapper().toJson(user));
	}
}
