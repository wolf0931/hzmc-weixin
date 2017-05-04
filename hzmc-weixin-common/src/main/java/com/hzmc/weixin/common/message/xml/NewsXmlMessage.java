package com.hzmc.weixin.common.message.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.hzmc.weixin.common.message.MsgType;
import com.hzmc.weixin.common.message.News;
import com.hzmc.weixin.common.message.XmlMessageHeader;

import java.util.Date;

@JacksonXmlRootElement(localName = "xml")
public class NewsXmlMessage extends XmlMessageHeader {

    @JsonProperty("ArticleCount")
    private int articleCount;
    @JsonProperty("Articles")
    private News news;

    public NewsXmlMessage() {
        this.msgType = MsgType.news;
        this.setCreateTime(new Date());
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public int getArticleCount() {
        return news.getArticles().size();
    }
}
