package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.base.BaseController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* WxUsercontroller
* Created by wph on 2017/5/4.
*/
@Controller
@RequestMapping("/WxUser")
@Api(value = "微信用户控制器", description = "微信用户管理")
public class WxUserController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(WxUserController.class);



}