package com.hzmc.weixin.admin.util;

import com.hzmc.weixin.admin.dao.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wph on 2017/5/12.
 */
public class UserSessionManger {
	/**
	 * logger
	 */
	private Logger LOGGER = LoggerFactory.getLogger(UserSessionManger.class);


	/**
	 * userInfoMap
	 */
	private static Map<String, User> userInfoMap =
			new HashMap<String, User>();

	/**
	 * 临时结果集超时时间：20分钟
	 */
	private int USER_SESSION_TIME_OUT = 20 * 60 * 1000;

	/**
	 *
	 */
	@Scheduled(fixedRate = 30 * 1000)
	public void checkTimeOut() {
		try {
			Set<String> timeOutSet = new HashSet<String>();
			for (String key : getUserInfoMap().keySet()) {
				User user = getUserInfoMap().get(key);
				long lastUpdateTime = user.getLastUpdateTime();
				if ((System.currentTimeMillis() - lastUpdateTime)
						>= USER_SESSION_TIME_OUT) {
					timeOutSet.add(key);
				}
			}
			for (String key : timeOutSet) {
				User user = getUserInfoMap().get(key);
				getUserInfoMap().remove(key);
				LOGGER.info("清除超时登录用户，sessionId：" + key);
			}
		} catch (NumberFormatException e) {
			LOGGER.error("检查会话超时出错", e);
		}
	}

	/**
	 * 根据userId查询sessionId.
	 *
	 * @param userId userId
	 * @return 用户id对应的有session，则返回sessionId,否则返回null
	 */
	public static String getSessionIdByUserId(long userId) {
		if (!getUserInfoMap().isEmpty()) {
			for (String key : getUserInfoMap().keySet()) {
				User user = getUserInfoMap().get(key);
				if (user != null && user.getId() == userId) {
					return key;
				}
			}
		}
		return null;
	}

	/**
	 * @param sessionId sessionId
	 * @param user      user
	 */
	public static void add(String sessionId, User user) {
		user.setLastUpdateTime(System.currentTimeMillis());
		getUserInfoMap().put(sessionId, user);
	}

	/**
	 * @param sessionId sessionId
	 * @return UserView
	 */
	public static User get(String sessionId) {
		User user = getUserInfoMap().get(sessionId);
		if (user != null) {
			user.setLastUpdateTime(System.currentTimeMillis());
		}
		return user;
	}

	/**
	 * @return the userInfoMap
	 */
	public static Map<String, User> getUserInfoMap() {
		return userInfoMap;
	}

	/**
	 * @param userInfoMap the userInfoMap to set
	 */
	public static void setUserInfoMap(Map<String, User> userInfoMap) {
		UserSessionManger.userInfoMap = userInfoMap;
	}
}
