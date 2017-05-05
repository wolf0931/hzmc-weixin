package com.hzmc.weixin.admin.controller;

import com.hzmc.weixin.admin.service.RedPayService;
import com.hzmc.weixin.pay.redpack.bean.RedPackRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wph on 2017/4/19.
 */
@Controller
@RequestMapping("/wx/oauth")
public class OauthController {

	private static Logger LOGGER = Logger.getLogger(OauthController.class);

	@Autowired
	private RedPayService redPayService;

	@RequestMapping(value = "",method = RequestMethod.GET)
	public void Oauth(){
		redPayService.sendSingleRed(new RedPackRequest());
	}
	/*@RequestMapping(value = "/",method = RequestMethod.GET)
	public String Oauth(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		LOGGER.info(code);
		AccessToken token = MpOAuth2s.defaultOAuth2s().getAccessToken(code);
		RedPackRequest redPackRequest = new RedPackRequest();
		redPackRequest.setAppId("wx535dc2a26c1e921f");
		redPackRequest.setActivityName("欢迎光临");
		redPackRequest.setAmount(600);
		redPackRequest.setBillNumber("1292063901201512030010000022");
		redPackRequest.setNumber(3);
		redPackRequest.setOpenId("oJvITt-VfGOTCe0dcXsZPCqn1APM");
		redPackRequest.setRemark("测试裂变红包");
		redPackRequest.setWishing("欢迎光临");
		redPackRequest.setSendName("美创");
		RedPackResponse redPackResponse = RedPacks.defaultRedPacks().sendGroup(redPackRequest);
		return "oauth";
	}*/
}
