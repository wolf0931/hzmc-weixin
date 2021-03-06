package com.hzmc.weixin.pay;


import com.hzmc.weixin.common.WxSslClient;
import com.hzmc.weixin.pay.base.PaySetting;

import java.util.concurrent.ConcurrentHashMap;

public class PayWxClientFactory {

    private static PayWxClientFactory instance = null;
    private static ConcurrentHashMap<String, WxSslClient> wxClients = new ConcurrentHashMap<>();

    public static PayWxClientFactory getInstance() {
        if (instance == null) {
            instance = new PayWxClientFactory();
        }
        return instance;
    }

    public WxSslClient defaultWxSslClient() {
        return with(PaySetting.defaultSetting());
    }

    public WxSslClient with(PaySetting paySetting) {
        if (!wxClients.containsKey(key(paySetting))) {
            WxSslClient wxClient = new WxSslClient(paySetting.getCertPath(), paySetting.getCertPassword());
            wxClients.putIfAbsent(key(paySetting), wxClient);
        }

        return wxClients.get(key(paySetting));
    }


    private String key(PaySetting paySetting) {
        return paySetting.getAppId() + ":" + paySetting.getMchId();
    }
}

