package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxPayRecord;
import com.hzmc.weixin.admin.dao.model.WxRedpackTemplet;
import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.service.RedPayService;
import com.hzmc.weixin.admin.service.WxPayRecordService;
import com.hzmc.weixin.admin.service.WxRedpackTempletService;
import com.hzmc.weixin.admin.service.WxUserService;
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

	@Autowired
	private WxUserService userService;

	@Autowired
	private WxRedpackTempletService wxRedpackTempletService;

	@Override
	public Object sendSingleRed(WxUser wxUser, int id) {
		//单个红包
		//102 为员工不能发红包 oJvITt-VfGOTCe0dcXsZPCqn1APM
		RedPackRequest redPackRequest = new RedPackRequest();
		User user = Users.defaultUsers().get(wxUser.getOpenid());
		WxUser wxUser1 = userService.getWxUserByOpenId(wxUser.getOpenid());
		if (user.getGroup() == 102 || wxUser1.getGroupid() == 102) {
			return new Result(ResultConstant.FAILED, "内部人员不能发红包");
		} else if (!user.isSubscribed()) {
			return new Result(ResultConstant.FAILED, "没有关注公众号");
		}
		WxRedpackTemplet wxRedpackTemplet = wxRedpackTempletService.selectByPrimaryKey(id);
		redPackRequest.setAppId(PaySetting.defaultSetting().getAppId());
		redPackRequest.setActivityName(wxRedpackTemplet.getActName());
		Random random = new Random();
		int max = Integer.valueOf(wxRedpackTemplet.getMaxAmount()) * 100;
		int min = Integer.valueOf(wxRedpackTemplet.getMinAmount()) * 100;
		int randomSum = random.nextInt(max - min);
		redPackRequest.setAmount(min + randomSum);
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMdd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		redPackRequest.setBillNumber(PaySetting.defaultSetting().getMchId() + retStrFormatNowDate + RandomStringGenerator.getRandomIntByLength(10));
		redPackRequest.setNumber(1);
		redPackRequest.setOpenId(wxUser.getOpenid());
		redPackRequest.setRemark(wxRedpackTemplet.getRemark());
		redPackRequest.setWishing(wxRedpackTemplet.getWishing());
		redPackRequest.setSendName(wxRedpackTemplet.getSendName());
		RedPackResponse redPackResponse = RedPacks.defaultRedPacks().sendSingle(redPackRequest);
		if (redPackResponse.success()) {
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
