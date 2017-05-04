package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.service.AccessService;
import com.hzmc.weixin.admin.service.RedPayService;
import com.hzmc.weixin.admin.util.MessageUtil;
import com.hzmc.weixin.common.event.EventType;
import com.hzmc.weixin.common.message.Article;
import com.hzmc.weixin.common.message.News;
import com.hzmc.weixin.common.message.XmlMessageHeader;
import com.hzmc.weixin.common.message.xml.NewsXmlMessage;
import com.hzmc.weixin.common.request.TextRequest;
import com.hzmc.weixin.mp.event.ticket.SceneSubEvent;
import com.hzmc.weixin.mp.message.MpXmlMessages;
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
	private RedPayService redPayService;

	@Override
	public String processRequest(HttpServletRequest request) {
		LOGGER.info("processRequest");
		String result = null;
		//String url = MpOAuth2s.defaultOAuth2s().authenticationUrl("http://09d9db0b.ngrok.io/wx/oauth/", "snsapi_base");
		XmlMessageHeader xmlMessageHeader = MpXmlMessages.fromXml(MessageUtil.parseMsgXml(request));
		if (xmlMessageHeader instanceof TextRequest) {
			/*if (((TextRequest) xmlMessageHeader).getContent().equals("test")){
				User user = Users.defaultUsers().get(xmlMessageHeader.getFromUser());
				NewsXmlMessage newsXmlMessage = new NewsXmlMessage();
				News news = new News();
				Article article1 = new Article();
				article1.setTitle("[1]测试news");
				article1.setDescription("[1]今日头条，正在调试message API, 测试是否能正常发送news类型。");
				article1.setUrl(url);
				//article1.setPicUrl("http://wx.qlogo.cn/mmopen/ajNVdqHZLLCxVL7PbRb7roIcC7TPHNKbO3HrFpCSq7gyiaea7265PvyfzF3Mv7eFBLZqnAw7Q3Megb6jVaNFGXg/0");
				news.add(article1);

				newsXmlMessage.setNews(news);
				newsXmlMessage.setFromUser(xmlMessageHeader.getToUser());
				newsXmlMessage.setToUser(xmlMessageHeader.getFromUser());
				newsXmlMessage.setCreateTime(new Date());
				result = MpXmlMessages.toXml(newsXmlMessage);
				LOGGER.info(result);
			}*/
		} else if (xmlMessageHeader instanceof SceneSubEvent) {
			if (((SceneSubEvent) xmlMessageHeader).getEventType() == EventType.subscribe) {
				//RedPackRequest redPackRequest = new RedPackRequest();
				//redPackRequest.setOpenId(xmlMessageHeader.getFromUser());
				//redPayService.sendSingleRed(redPackRequest);
				User user = Users.defaultUsers().get(xmlMessageHeader.getFromUser());
				NewsXmlMessage newsXmlMessage = new NewsXmlMessage();
				News news = new News();
				Article article1 = new Article();
				article1.setTitle("红包主题");
				article1.setDescription("红包主题。");
				article1.setUrl("http://09d9db0b.ngrok.io/wx/oauth/");
				//article1.setPicUrl("http://wx.qlogo.cn/mmopen/ajNVdqHZLLCxVL7PbRb7roIcC7TPHNKbO3HrFpCSq7gyiaea7265PvyfzF3Mv7eFBLZqnAw7Q3Megb6jVaNFGXg/0");
				news.add(article1);
				newsXmlMessage.setNews(news);
				newsXmlMessage.setFromUser(xmlMessageHeader.getToUser());
				newsXmlMessage.setToUser(xmlMessageHeader.getFromUser());
				newsXmlMessage.setCreateTime(new Date());
				result = MpXmlMessages.toXml(newsXmlMessage);

			}
		}

		return result;
	}
}
