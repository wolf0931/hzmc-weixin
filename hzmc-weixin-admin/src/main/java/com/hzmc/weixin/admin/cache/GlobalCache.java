//*********************************************************************
//系统名称：DBRDR
//Copyright(C)2000-2016 NARI Information and Communication Technology
//Branch. All rights reserved.
//版本信息：DBRDR-V1.000
//#作者：徐小倩  权重：100%#
//版本                  日期         作者       变更记录
//DBRDR-V1.000         2017年2月13日      徐小倩　     新建
//*********************************************************************
package com.hzmc.weixin.admin.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GlobalCache {

    /**
     * cache map.
     */
    public static final Map<String, Object> CACHE_MAP =
            new ConcurrentHashMap<String, Object>();

    /**
     * add element.
     * @param key key
     * @param value value
     */
    public void addElement(String key, Object value) {
        CACHE_MAP.put(key, value);
    }

    /**
     * get element by key.
     * @param key key
     * @return value
     */
    public Object getElement(String key) {
        return CACHE_MAP.get(key);
    }
}
