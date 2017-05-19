package com.hzmc.weixin.admin.service.impl;

import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.cache.GlobalCache;
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
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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
	public Object sendSingleRed(WxUser wxUser, int id, int voteId) {
		//单个红包
		//102 为员工不能发红包 oJvITt-VfGOTCe0dcXsZPCqn1APM
		WxRedpackTemplet wxRedpackTemplet = wxRedpackTempletService.selectByPrimaryKey(id);
		long curtime = System.currentTimeMillis() / 1000;
		long minTime = Long.valueOf(wxRedpackTemplet.getStartTime());
		long maxTime = Long.valueOf(wxRedpackTemplet.getEndTime());
		if (wxRedpackTemplet == null) {
			return new Result(ResultConstant.FAILED, "红包模板不存在");
		}
		if (!(curtime >= minTime && curtime <= maxTime)) {
			return new Result(ResultConstant.FAILED, "活动已结束");
		}
		User user = Users.defaultUsers().get(wxUser.getOpenid());
		if (user == null) {
			return new Result(ResultConstant.FAILED, "没有关注公众号");
		}
		WxUser wxUser1 = userService.getWxUserByOpenId(wxUser.getOpenid());
		if (user.getGroup() == 102 || wxUser1.getGroupid() == 102) {
			return new Result(ResultConstant.FAILED, "内部人员不能发红包");
		}
		if (GlobalCache.CACHE_MAP.get(wxUser.getOpenid()) == null) {
			Map<String, Object> re = new HashedMap();
			RedPackResponse redPackResponse = sendRed(wxUser, wxRedpackTemplet);
			if (redPackResponse.success()) {
				GlobalCache.CACHE_MAP.put(wxUser.getOpenid(), wxRedpackTemplet);
				if (voteId == 1) {
					GlobalCache.left.add(wxUser.getOpenid());
				} else {
					GlobalCache.right.add(wxUser.getOpenid());
				}
				re.put("left", GlobalCache.getLeft().size());
				re.put("right", GlobalCache.getRight().size());
				re.put("redPackResponse", redPackResponse);
				return new Result(ResultConstant.SUCCESS, re);
			} else {
				return new Result(ResultConstant.FAILED, redPackResponse.getReturnMessage());
			}
		} else if (GlobalCache.CACHE_MAP.get(wxUser.getOpenid()) != null) {
			return new Result(ResultConstant.FAILED, "已投票成功");
		}
		return null;
	}

	private RedPackResponse sendRed(WxUser wxUser, WxRedpackTemplet wxRedpackTemplet) {
		RedPackRequest redPackRequest = new RedPackRequest();
		redPackRequest.setAppId(PaySetting.defaultSetting().getAppId());
		redPackRequest.setActivityName(wxRedpackTemplet.getActName());
		Random random = new Random();
		Double max = Double.valueOf(wxRedpackTemplet.getMaxAmount()) * 100;
		int iMax = max.intValue();
		Double min = Double.valueOf(wxRedpackTemplet.getMinAmount()) * 100;
		int iMin = min.intValue();
		int randomSum = random.nextInt(iMax - iMin);
		redPackRequest.setAmount(iMin + randomSum);
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
			wxPayRecord.setRedpacktemid(wxRedpackTemplet.getId());
			wxPayRecord.setOpenid(redPackResponse.getOpenId());
			wxPayRecord.setMchId(PaySetting.defaultSetting().getMchId());
			wxPayRecord.setSendListid(redPackResponse.getSendListId());
			wxPayRecord.setTotalAmount(redPackResponse.getAmount());
			wxPayRecord.setStatus("SENT");
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
