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
import java.util.Random;

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
		Random random = new Random();
		int sum = random.nextInt(10);
		redPackRequest.setAmount(100 + sum);
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMdd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		redPackRequest.setBillNumber(PaySetting.defaultSetting().getMchId() + retStrFormatNowDate + RandomStringGenerator.getRandomIntByLength(10));
		redPackRequest.setNumber(1);
		redPackRequest.setOpenId("oJvITt-VfGOTCe0dcXsZPCqn1APM");
		redPackRequest.setRemark("测试发红包");
		redPackRequest.setWishing("恭喜发财");
		redPackRequest.setSendName("美创科技");
		return RedPacks.defaultRedPacks().sendSingle(redPackRequest);
	}

	@Override
	public RedPackResponse sendGroupRed(RedPackRequest redPackRequest) {
		return RedPacks.defaultRedPacks().sendGroup(redPackRequest);
	}
}
