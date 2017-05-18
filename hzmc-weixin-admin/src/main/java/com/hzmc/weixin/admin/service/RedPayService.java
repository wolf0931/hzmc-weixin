package com.hzmc.weixin.admin.service;

import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.pay.redpack.bean.RedPackRequest;
import com.hzmc.weixin.pay.redpack.bean.RedPackResponse;

/**
 * Created by wph on 2017/4/26.
 */
public interface RedPayService {

	Object sendSingleRed(WxUser wxUser, int id, int voteId);


	RedPackResponse sendGroupRed(RedPackRequest redPackRequest);

}
