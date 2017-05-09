package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.dao.model.WxPayRecord;
import com.hzmc.weixin.admin.service.RedPayService;
import com.hzmc.weixin.admin.service.WxPayRecordService;
import com.hzmc.weixin.common.util.RandomStringGenerator;
import com.hzmc.weixin.mp.user.Users;
import com.hzmc.weixin.mp.user.bean.User;
import com.hzmc.weixin.pay.base.PaySetting;
import com.hzmc.weixin.pay.redpack.RedPacks;
import com.hzmc.weixin.pay.redpack.bean.RedPackRequest;
import com.hzmc.weixin.pay.redpack.bean.RedPackResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by wph on 2017/4/26.
 */
@Service
public class RedPayServiceImp implements RedPayService {

	private static Logger LOGGER = LoggerFactory.getLogger(RedPayServiceImp.class);

	@Autowired
	private WxPayRecordService wxPayRecordService;

	@Override
	public RedPackResponse sendSingleRed(RedPackRequest redPackRequest) {
		//单个红包
		User user = Users.defaultUsers().get("oJvITt-VfGOTCe0dcXsZPCqn1APM");
		//102 为员工不能发红包
		/*if (user.getGroup() == 102) {
			return null;
		}*/
		redPackRequest.setAppId(PaySetting.defaultSetting().getAppId());
		redPackRequest.setActivityName("土豪发红包");
		Random random = new Random();
		int sum = random.nextInt(50);
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
		RedPackResponse redPackResponse = RedPacks.defaultRedPacks().sendSingle(redPackRequest);
		if (redPackResponse.success()){
			//插入记录
			LOGGER.info("红包发送成功，插入红包记录");
			WxPayRecord wxPayRecord = new WxPayRecord();
			wxPayRecord.setMchBillno(redPackResponse.getBillNumber());
			wxPayRecord.setOpenid(redPackResponse.getOpenId());
			wxPayRecord.setMchId(PaySetting.defaultSetting().getMchId());
			wxPayRecord.setSendListid(redPackResponse.getSendListId());
			wxPayRecord.setTotalAmount(redPackResponse.getAmount());
			wxPayRecord.setWxappid(PaySetting.defaultSetting().getAppId());
			wxPayRecord.setCtime(String.valueOf(System.currentTimeMillis()));
			wxPayRecordService.insert(wxPayRecord);
		}
		return redPackResponse;
	}

	@Override
	public RedPackResponse sendGroupRed(RedPackRequest redPackRequest) {
		return RedPacks.defaultRedPacks().sendGroup(redPackRequest);
	}
}
