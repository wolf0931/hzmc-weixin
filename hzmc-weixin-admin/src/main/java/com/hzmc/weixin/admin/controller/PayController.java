package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.service.RedPayService;
import com.hzmc.weixin.common.util.JsonMapper;
import com.hzmc.weixin.pay.redpack.RedPacks;
import com.hzmc.weixin.pay.redpack.bean.RedPackRequest;
import com.hzmc.weixin.pay.redpack.bean.RedPackResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wph on 2017/4/19.
 */
@RestController
@RequestMapping("/wx/pay")
@Api(value = "发红包控制器",description = "发红包控制器")
public class PayController {

	@Autowired
	private RedPayService redPayService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void pay(@RequestBody RedPackRequest redPackRequest) {
		redPayService.sendSingleRed(redPackRequest);
	}

	@RequestMapping(value = "/query/{billNumber}", method = RequestMethod.GET)
	public Object payQuery(@PathVariable String billNumber) {
		RedPackResult redPackResult = RedPacks.defaultRedPacks().query(billNumber);
		return JsonMapper.defaultMapper().toJson(redPackResult);
	}

}
