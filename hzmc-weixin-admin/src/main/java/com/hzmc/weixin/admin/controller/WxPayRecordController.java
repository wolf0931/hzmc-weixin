package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxPayRecord;
import com.hzmc.weixin.admin.dao.model.WxPayRecordExample;
import com.hzmc.weixin.admin.service.WxPayRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * WxPayRecordcontroller
 * Created by wph on 2017/5/8.
 */
@RestController
@RequestMapping("/WxPayRecord")
@Api(value = "微信支付记录控制器", description = "微信支付记录管理")
public class WxPayRecordController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(WxPayRecordController.class);

	@Autowired
	private WxPayRecordService wxPayRecordService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "得到所有已经发出发去的红包记录")
	private Object getPayRecordLists() {
		WxPayRecordExample wxPayRecordExample = new WxPayRecordExample();
		List<WxPayRecord> wxPayRecords = wxPayRecordService.selectByExample(wxPayRecordExample);
		return new Result(ResultConstant.SUCCESS, wxPayRecords);
	}

}