package com.hzmc.weixin.common.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

    /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String md5Hex(String origin) {
        return DigestUtils.md5Hex(origin);
    }

}
