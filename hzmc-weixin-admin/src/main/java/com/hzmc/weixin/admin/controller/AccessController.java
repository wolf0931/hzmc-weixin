package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.service.AccessService;
import com.hzmc.weixin.admin.util.CheckoutUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@Api(value = "微信服务认证",description = "微信认证和信息管理")
public class AccessController {

	private static Logger LOGGER = Logger.getLogger(AccessController.class);

	@Autowired
	private AccessService accessService;

	@RequestMapping(value = "/access", method = RequestMethod.GET)
	public void token(HttpServletRequest request, HttpServletResponse response) {
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		PrintWriter print;
		if (isGet) {
			// 微信加密签名
			String signature = request.getParameter("signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			LOGGER.info(signature + "----" + timestamp + "---" + nonce + "+" + echostr);
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
				try {
					print = response.getWriter();
					print.write(echostr);
					print.flush();
					LOGGER.info(echostr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	@ApiOperation(value = "消息处理")
	public void postToken(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		String responseMessage = accessService.processRequest(request);
		out.print(responseMessage);
		out.flush();
	}

}
