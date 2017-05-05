package com.hzmc.weixin.admin.interceptor;

import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.service.WxUserService;
import com.hzmc.weixin.admin.util.SpringContextUtil;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.UserPagination;
import org.apache.log4j.Logger;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * Created by wph on 2017/5/4.
 */

public class ApplicationEventListener implements ApplicationListener{

	private static Logger LOGGER = Logger.getLogger(ApplicationEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ApplicationEnvironmentPreparedEvent) {
			LOGGER.info(" 初始化环境变量");
		} else if (event instanceof ApplicationPreparedEvent) {
			LOGGER.info("初始化完成");
		} else if (event instanceof ContextRefreshedEvent) {
			LOGGER.info("应用刷新");
		} else if (event instanceof ApplicationReadyEvent) {
			LOGGER.info("启动已完成");
			//insertDb();
		} else if (event instanceof ContextClosedEvent) {
			LOGGER.info("应用关闭");
		}
	}


	/*@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		insertDb();
	}*/


	private void insertDb() {
		WxUserService wxUserService = SpringContextUtil.getBean(WxUserService.class);
		UserPagination userPagination = Users.defaultUsers().list();
		List<String> users = userPagination.getUsers();
		for (String opeonId : users) {
			com.hzmc.weixin.mp.user.bean.User user = Users.defaultUsers().get(opeonId);
			WxUser wxUser = new WxUser();
			wxUser.setOpenid(user.getOpenId());
			wxUser.setProvince(user.getProvince());
			wxUser.setCity(user.getCity());
			wxUser.setCountry(user.getCountry());
			wxUser.setHeadimgurl(user.getHeadImgUrl());
			wxUser.setGroupid(user.getGroup());
			wxUser.setNickname(user.getNickName());
			wxUser.setRemark(user.getRemark());
			wxUser.setSex(user.getSex().getCode());
			if (user.getUnionId() != null) {
				wxUser.setUnionid(Integer.valueOf(user.getUnionId()));
			}
			if (user.isSubscribed()) {
				wxUser.setSubscribe(1);
			} else {
				wxUser.setSubscribe(0);
			}
			wxUser.setSubscribeTime(user.getSubscribedTime());
			StringBuilder sb = new StringBuilder();
			if (user.getTags() != null) {
				for (Integer tag : user.getTags()) {
					sb.append(tag);
					sb.append(",");
				}
			}
			wxUser.setTagidList(sb.toString());
			wxUserService.insert(wxUser);
		}
	}
}
