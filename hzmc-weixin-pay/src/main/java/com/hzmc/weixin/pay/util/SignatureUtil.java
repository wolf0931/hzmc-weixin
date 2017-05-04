package com.hzmc.weixin.pay.util;

import com.google.common.base.Joiner;
import com.hzmc.weixin.common.util.MD5;

import java.util.Map;


public class SignatureUtil {

    public static String sign(Map<String, Object> map, String key){
        String str = Joiner.on("&").withKeyValueSeparator("=").join(map);
        str += "&key=" + key;
        return MD5.md5Hex(str).toUpperCase();
    }
}
