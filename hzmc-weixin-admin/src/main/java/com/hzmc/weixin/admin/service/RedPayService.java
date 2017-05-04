package com.hzmc.weixin.admin.service;

import com.hzmc.weixin.pay.redpack.bean.RedPackRequest;
import com.hzmc.weixin.pay.redpack.bean.RedPackResponse;

/**
 * Created by wph on 2017/4/26.
 */
public interface RedPayService {

	RedPackResponse sendSingleRed(RedPackRequest redPackRequest);


	RedPackResponse sendGroupRed(RedPackRequest redPackRequest);

}
