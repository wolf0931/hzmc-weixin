package com.hzmc.weixin.admin.interceptor;

import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.dao.model.WxUserExample;
import com.hzmc.weixin.admin.service.GroupService;
import com.hzmc.weixin.admin.service.WxUserService;
import com.hzmc.weixin.admin.util.SpringContextUtil;
import com.hzmc.weixin.mp.user.Groups;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.Group;
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

import static com.hzmc.weixin.admin.util.SpringContextUtil.getBean;

/**
 * Created by wph on 2017/5/4.
 */

public class ApplicationEventListener implements ApplicationListener {

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
			/*new Thread(new Runnable() {
				@Override
				public void run() {
					LOGGER.info("开始插入数据");
					//insertGroupDb();
					//insertUserDb();
				}
			}).start();*/
		} else if (event instanceof ContextClosedEvent) {
			LOGGER.info("应用关闭");
		}
	}

	private void insertGroupDb() {
		GroupService groupService = SpringContextUtil.getBean(GroupService.class);
		List<Group> groups = Groups.defaultGroups().list();
		for (Group g : groups) {
			com.hzmc.weixin.admin.dao.model.Group group = new com.hzmc.weixin.admin.dao.model.Group();
			group.setId(g.getId());
			group.setCount(g.getCount());
			group.setName(g.getName());
			if (groupService.getGroupByName(g.getName()) != null) {

			} else {
				groupService.insert(group);
			}
		}
	}

	private void insertUserDb() {
		WxUserService wxUserService = getBean(WxUserService.class);
		UserPagination userPagination = Users.defaultUsers().list();
		int count = userPagination.getCount();
		WxUserExample userExample = new WxUserExample();
		int sqlCount = wxUserService.countByExample(userExample);
		if (count > sqlCount) {
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
				LOGGER.info(wxUser.toString());
				WxUser wxUser1 = wxUserService.getWxUserByOpenId(opeonId);
				if (wxUser1 != null) {
					LOGGER.info("数据已存在");
				} else {
					LOGGER.info("添加数据");
					wxUserService.insertSelective(wxUser);
				}

			}
		}

	}
}
