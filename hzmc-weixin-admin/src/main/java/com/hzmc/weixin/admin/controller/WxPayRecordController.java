package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* WxPayRecordcontroller
* Created by wph on 2017/5/8.
*/
@Controller
@RequestMapping("/WxPayRecord")
@Api(value = "微信支付记录控制器", description = "微信支付记录管理")
public class WxPayRecordController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(WxPayRecordController.class);

}