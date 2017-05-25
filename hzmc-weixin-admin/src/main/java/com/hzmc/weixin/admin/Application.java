package com.hzmc.weixin.admin;

import com.hzmc.weixin.admin.dao.model.WxGroup;
import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.dao.model.WxUserExample;
import com.hzmc.weixin.admin.service.GroupService;
import com.hzmc.weixin.admin.service.WxUserService;
import com.hzmc.weixin.mp.user.Groups;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.Group;
import com.hzmc.weixin.mp.user.bean.UserPagination;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static com.hzmc.weixin.admin.util.SpringContextUtil.getBean;

@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {
	private static Logger LOGGER = Logger.getLogger(SpringBootServletInitializer.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Scheduled(fixedRate = 10 * 60 * 1000)
	private void initGroupDate() {
		insertGroupDb();
	}

	@Scheduled(fixedRate = 5 * 60 * 1000)
	private void initUserDate() {
		insertUserDb();
	}

	private void insertGroupDb() {
		GroupService groupService = getBean(GroupService.class);
		List<Group> groups = Groups.defaultGroups().list();
		for (Group g : groups) {
			com.hzmc.weixin.admin.dao.model.WxGroup wxGroup = new com.hzmc.weixin.admin.dao.model.WxGroup();
			wxGroup.setId(g.getId());
			wxGroup.setCount(g.getCount());
			wxGroup.setName(g.getName());
			WxGroup wxGroup1 = groupService.getGroupByName(g.getName());
			if (groupService.getGroupByName(g.getName()) != null) {
				groupService.updateByPrimaryKey(wxGroup1);
			} else {
				groupService.insert(wxGroup);
			}
		}
	}

	private void insertUserDb() {
		WxUserService wxUserService = getBean(WxUserService.class);
		UserPagination userPagination = Users.defaultUsers().list();
		int count = userPagination.getCount();
		WxUserExample userExample = new WxUserExample();
		int sqlCount = wxUserService.countByExample(userExample);
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
			wxUser.setNickname(wxUser.getNickname().replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
			if (wxUser1 != null) {
				LOGGER.info("数据已存在");
				wxUser.setId(wxUser1.getId());
				wxUserService.updateByPrimaryKey(wxUser);
			} else {
				LOGGER.info("添加数据" + wxUser.toString());
				wxUserService.insertSelective(wxUser);
			}
			System.out.println("用户信息" + wxUser.toString());
		}
	}
}
