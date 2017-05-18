package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.cache.GlobalCache;
import com.hzmc.weixin.admin.dao.model.WxRedpackTemplet;
import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.service.AccessService;
import com.hzmc.weixin.admin.service.WxRedpackTempletService;
import com.hzmc.weixin.admin.service.WxUserService;
import com.hzmc.weixin.admin.util.MessageUtil;
import com.hzmc.weixin.common.event.EventType;
import com.hzmc.weixin.common.message.Article;
import com.hzmc.weixin.common.message.News;
import com.hzmc.weixin.common.message.XmlMessageHeader;
import com.hzmc.weixin.common.message.xml.NewsXmlMessage;
import com.hzmc.weixin.common.request.TextRequest;
import com.hzmc.weixin.mp.event.ticket.SceneSubEvent;
import com.hzmc.weixin.mp.message.MpXmlMessages;
import com.hzmc.weixin.mp.oauth2.MpOAuth2s;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by wph on 2017/4/17.
 */
@Service
public class AccessServiceImp implements AccessService {

	private static Logger LOGGER = Logger.getLogger(AccessService.class);

	@Autowired
	private WxUserService wxUserService;

	@Autowired
	private WxRedpackTempletService wxRedpackTempletService;

	@Override
	public String processRequest(HttpServletRequest request) {
		LOGGER.info("processRequest");
		//String url = MpOAuth2s.defaultOAuth2s().authenticationUrl("http://09d9db0b.ngrok.io/wx/oauth/", "snsapi_base");
		XmlMessageHeader xmlMessageHeader = MpXmlMessages.fromXml(MessageUtil.parseMsgXml(request));
		if (xmlMessageHeader instanceof TextRequest) {
			if (((TextRequest) xmlMessageHeader).getContent().equals("test")) {
				WxRedpackTemplet wxRedpackTemplet = wxRedpackTempletService.selectByPrimaryKey(1);
				long curtime = System.currentTimeMillis() / 1000;
				long min = Long.valueOf(wxRedpackTemplet.getStartTime());
				long max = Long.valueOf(wxRedpackTemplet.getEndTime());
				if (curtime >= min && curtime <= max) {
					return sendXml(xmlMessageHeader, wxRedpackTemplet);
				}
			}
		} else if (xmlMessageHeader instanceof SceneSubEvent) {
			if (((SceneSubEvent) xmlMessageHeader).getEventType() == EventType.subscribe) {
				WxRedpackTemplet wxRedpackTemplet = wxRedpackTempletService.selectByPrimaryKey(1);
				String fromUser = xmlMessageHeader.getFromUser();
				User user = Users.defaultUsers().get(fromUser);
				WxUser user1 = wxUserService.getWxUserByOpenId(fromUser);
				LOGGER.info("关注返回的信息" + user.toString());
				if (user1 == null) {
					insertDb(user);
				} else {
					updateDb(user);
				}
				if (GlobalCache.CACHE_MAP.get(fromUser) != null) {
					return null;
				}
				long curtime = System.currentTimeMillis() / 1000;
				long min = Long.valueOf(wxRedpackTemplet.getStartTime());
				long max = Long.valueOf(wxRedpackTemplet.getEndTime());
				if (curtime >= min && curtime <= max) {
					return sendXml(xmlMessageHeader, wxRedpackTemplet);
				}
			} else if (((SceneSubEvent) xmlMessageHeader).getEventType() == EventType.unsubscribe) {
				String fromUser = xmlMessageHeader.getFromUser();
				WxUser user1 = wxUserService.getWxUserByOpenId(fromUser);
				LOGGER.info("取消关注" + user1.toString());
				if (user1 != null) {
					wxUserService.deleteByPrimaryKey(user1.getId());
				}
			}
		}
		return null;
	}

	private String sendXml(XmlMessageHeader xmlMessageHeader, WxRedpackTemplet wxRedpackTemplet) {
		String url = MpOAuth2s.defaultOAuth2s().authenticationUrl("http://79269421.ngrok.io/view_mobile/index.html", "snsapi_base");
		//String url  = "http://09d9db0b.ngrok.io/src/view_mobile/index.html";
		NewsXmlMessage newsXmlMessage = new NewsXmlMessage();
		News news = new News();
		Article article1 = new Article();
		article1.setTitle(wxRedpackTemplet.getActName());
		article1.setDescription(wxRedpackTemplet.getRemark());
		article1.setUrl(url);
		news.add(article1);
		newsXmlMessage.setNews(news);
		newsXmlMessage.setFromUser(xmlMessageHeader.getToUser());
		newsXmlMessage.setToUser(xmlMessageHeader.getFromUser());
		newsXmlMessage.setCreateTime(new Date());
		return MpXmlMessages.toXml(newsXmlMessage);
	}

	private int insertDb(User user) {
		WxUser user2 = new WxUser();
		user2.setSubscribe(0);
		if (user.isSubscribed()) {
			user2.setSubscribe(1);
		}
		if (user.getUnionId() != null) {
			user2.setUnionid(Integer.valueOf(user.getUnionId()));
		}
		user2.setRemark(user.getRemark());
		user2.setNickname(user.getNickName());
		user2.setCity(user.getCity());
		user2.setCountry(user.getCountry());
		user2.setHeadimgurl(user.getHeadImgUrl());
		user2.setProvince(user.getProvince());
		user2.setSex(user.getSex().getCode());
		user2.setOpenid(user.getOpenId());
		user2.setSubscribeTime(user.getSubscribedTime());
		user2.setGroupid(user.getGroup());
		StringBuilder sb = new StringBuilder();
		if (user.getTags() != null) {
			for (Integer tag : user.getTags()) {
				sb.append(tag);
				sb.append(",");
			}
		}
		user2.setTagidList(sb.toString());
		return wxUserService.insert(user2);
	}

	private int updateDb(User user) {
		WxUser user3 = new WxUser();
		user3.setSubscribe(0);
		if (user.isSubscribed()) {
			user3.setSubscribe(1);
		}
		if (user.getUnionId() != null) {
			user3.setUnionid(Integer.valueOf(user.getUnionId()));
		}
		user3.setRemark(user.getRemark());
		user3.setNickname(user.getNickName());
		user3.setCity(user.getCity());
		user3.setCountry(user.getCountry());
		user3.setHeadimgurl(user.getHeadImgUrl());
		user3.setProvince(user.getProvince());
		user3.setSex(user.getSex().getCode());
		user3.setOpenid(user.getOpenId());
		user3.setSubscribeTime(user.getSubscribedTime());
		user3.setGroupid(user.getGroup());
		StringBuilder sb = new StringBuilder();
		if (user.getTags() != null) {
			for (Integer tag : user.getTags()) {
				sb.append(tag);
				sb.append(",");
			}
		}
		user3.setTagidList(sb.toString());
		return wxUserService.updateWxUserByOpenId(user3);
	}
}
