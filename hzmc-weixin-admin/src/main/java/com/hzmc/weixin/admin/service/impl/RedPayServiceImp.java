package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.service.RedPayService;
import com.hzmc.weixin.common.util.RandomStringGenerator;
import com.hzmc.weixin.pay.base.PaySetting;
import com.hzmc.weixin.pay.redpack.RedPacks;
import com.hzmc.weixin.pay.redpack.bean.RedPackRequest;
import com.hzmc.weixin.pay.redpack.bean.RedPackResponse;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wph on 2017/4/26.
 */
@Service
public class RedPayServiceImp implements RedPayService {

	@Override
	public RedPackResponse sendSingleRed(RedPackRequest redPackRequest) {
		//单个红包
		redPackRequest.setAppId(PaySetting.defaultSetting().getAppId());
		redPackRequest.setActivityName("土豪发红包");
		redPackRequest.setAmount(100);
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMdd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		redPackRequest.setBillNumber(PaySetting.defaultSetting().getMchId() + retStrFormatNowDate + RandomStringGenerator.getRandomIntByLength(10));
		//redPackRequest.setBillNumber("1292063901201605150012300031");
		redPackRequest.setNumber(1);
		redPackRequest.setOpenId("oJvITt-VfGOTCe0dcXsZPCqn1APM");
		redPackRequest.setRemark("测试发红包");
		redPackRequest.setWishing("恭喜发财");
		redPackRequest.setSendName("美创科技");
		/*//裂变红包
		redPackRequest.setAppId("wx535dc2a26c1e921f");
		redPackRequest.setActivityName("欢迎光临");
		redPackRequest.setAmount(3);
		redPackRequest.setBillNumber("1292063901201512030010000022");
		redPackRequest.setNumber(3);
		redPackRequest.setOpenId("oJvITt-VfGOTCe0dcXsZPCqn1APM");
		redPackRequest.setRemark("测试裂变红包");
		redPackRequest.setWishing("欢迎光临");
		redPackRequest.setSendName("美创");*/
		return RedPacks.defaultRedPacks().sendSingle(redPackRequest);
	}

	@Override
	public RedPackResponse sendGroupRed(RedPackRequest redPackRequest) {
		return RedPacks.defaultRedPacks().sendGroup(redPackRequest);
	}
}
