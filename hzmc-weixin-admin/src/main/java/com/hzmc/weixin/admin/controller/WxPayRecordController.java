package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxPayRecord;
import com.hzmc.weixin.admin.dao.model.WxPayRecordExample;
import com.hzmc.weixin.admin.service.WxPayRecordService;
import com.hzmc.weixin.admin.service.WxRedpackTempletService;
import com.hzmc.weixin.admin.service.WxUserService;
import com.hzmc.weixin.pay.redpack.RedPacks;
import com.hzmc.weixin.pay.redpack.bean.RedPackResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private WxUserService wxUserService;

	@Autowired
	private WxRedpackTempletService wxRedpackTempletService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "得到所有已经发出发去的红包记录")
	private Object getPayRecordLists() {
		WxPayRecordExample wxPayRecordExample = new WxPayRecordExample();
		List<WxPayRecord> wxPayRecords = wxPayRecordService.selectByExample(wxPayRecordExample);
		List<Map<String, Object>> list = new ArrayList<>();
		for (WxPayRecord wxPayRecord : wxPayRecords) {
			Map<String, Object> map = new HashedMap();
			if (wxPayRecord.getStatus().equals("RECEIVED")){
				map.put("wxPayRecord", wxPayRecord);
			}else{
				RedPackResult redPackResult = RedPacks.defaultRedPacks().query(wxPayRecord.getMchBillno());
				wxPayRecord.setStatus(redPackResult.getStatus());
				wxPayRecordService.updateByPrimaryKey(wxPayRecord);
				map.put("wxPayRecord", wxPayRecord);
			}
			map.put("user", wxUserService.getWxUserByOpenId(wxPayRecord.getOpenid()));
			map.put("redPackTem",wxRedpackTempletService.selectByPrimaryKey(wxPayRecord.getRedpacktemid()));
			list.add(map);
		}
		return new Result(ResultConstant.SUCCESS, list);
	}

}