package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxUser;
import com.hzmc.weixin.admin.service.RedPayService;
import com.hzmc.weixin.pay.redpack.RedPacks;
import com.hzmc.weixin.pay.redpack.bean.RedPackResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "发红包控制器", description = "发红包控制器")
public class PayController {

	@Autowired
	private RedPayService redPayService;

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "根据红包模板ID发红包")
	public Object pay(@RequestBody WxUser wxUser, @PathVariable int id) {
		return redPayService.sendSingleRed(wxUser, id);
	}

	@RequestMapping(value = "/query/{billNumber}", method = RequestMethod.GET)
	@ApiOperation(value = "根据billNumber查询红包详情")
	public Object payQuery(@PathVariable String billNumber) {
		RedPackResult redPackResult = RedPacks.defaultRedPacks().query(billNumber);
		return new Result(ResultConstant.SUCCESS, redPackResult);
	}

}
