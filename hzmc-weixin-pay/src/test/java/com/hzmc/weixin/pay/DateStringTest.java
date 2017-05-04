package com.hzmc.weixin.pay;

import com.hzmc.weixin.common.util.XmlObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wph on 2017/4/21.
 */
public class DateStringTest {

	@Test
	public void testBlack() {
		String dd = "<xml>\n" +
				"<return_code><![CDATA[SUCCESS]]></return_code>\n" +
				"<return_msg><![CDATA[OK]]></return_msg>\n" +
				"<result_code><![CDATA[SUCCESS]]></result_code>\n" +
				"<err_code><![CDATA[SUCCESS]]></err_code>\n" +
				"<err_code_des><![CDATA[OK]]></err_code_des>\n" +
				"<mch_billno><![CDATA[1292063901201605150012300031]]></mch_billno>\n" +
				"<mch_id><![CDATA[1310292801]]></mch_id>\n" +
				"<detail_id><![CDATA[1000041701201704213000039731103]]></detail_id>\n" +
				"<status><![CDATA[RECEIVED]]></status>\n" +
				"<send_type><![CDATA[API]]></send_type>\n" +
				"<hb_type><![CDATA[GROUP]]></hb_type>\n" +
				"<total_num>3</total_num>\n" +
				"<total_amount>300</total_amount>\n" +
				"<send_time><![CDATA[2017-04-21 11:01:27]]></send_time>\n" +
				"<hblist>\n" +
				"<hbinfo>\n" +
				"<openid><![CDATA[oJvITt6DcWi0O0vM95761pS7g9Ug]]></openid>\n" +
				"<amount>1</amount>\n" +
				"<rcv_time><![CDATA[2017-04-21 11:06:29]]></rcv_time>\n" +
				"</hbinfo>\n" +
				"<hbinfo>\n" +
				"<openid><![CDATA[oJvITt1Q6Q2QRJq99z3v3KD6v5GM]]></openid>\n" +
				"<amount>102</amount>\n" +
				"<rcv_time><![CDATA[2017-04-21 11:03:24]]></rcv_time>\n" +
				"</hbinfo>\n" +
				"<hbinfo>\n" +
				"<openid><![CDATA[oJvITt-VfGOTCe0dcXsZPCqn1APM]]></openid>\n" +
				"<amount>197</amount>\n" +
				"<rcv_time><![CDATA[2017-04-21 11:01:43]]></rcv_time>\n" +
				"</hbinfo>\n" +
				"</hblist>\n" +
				"</xml>";
		String xml = "<xml>\n" +
				"<return_code><![CDATA[SUCCESS]]></return_code>\n" +
				"<return_msg><![CDATA[OK]]></return_msg>\n" +
				"<result_code><![CDATA[SUCCESS]]></result_code>\n" +
				"<err_code><![CDATA[SUCCESS]]></err_code>\n" +
				"<err_code_des><![CDATA[OK]]></err_code_des>\n" +


				"<mch_id><![CDATA[1310292801]]></mch_id>\n" +


				"<detail_id><![CDATA[1000041701201704213000039731103]]></detail_id>\n" +
				"<mch_billno><![CDATA[1292063901201605150012300031]]></mch_billno>\n" +
				"<send_type><![CDATA[API]]></send_type>\n" +
				"<hb_type><![CDATA[GROUP]]></hb_type>\n" +
				"<total_num>3</total_num>\n" +
				"<total_amount>300</total_amount>\n" +
				"<send_time><![CDATA[2017-04-21 11:01:27]]></send_time>\n" +

				"<hblist>\n" +
					"<hbinfo>\n" +
						"<openid><![CDATA[oJvITt6DcWi0O0vM95761pS7g9Ug]]></openid>\n" +
						"<amount>1</amount>\n" +
						"<rcv_time><![CDATA[2017-04-21 11:06:29]]></rcv_time>\n" +
						"</hbinfo>\n" +
					"<hbinfo>\n" +
						"<openid><![CDATA[oJvITt1Q6Q2QRJq99z3v3KD6v5GM]]></openid>\n" +
						"<amount>102</amount>\n" +
						"<rcv_time><![CDATA[2017-04-21 11:03:24]]></rcv_time>\n" +
					"</hbinfo>\n" +
					"<hbinfo>\n" +
						"<openid><![CDATA[oJvITt-VfGOTCe0dcXsZPCqn1APM]]></openid>\n" +
						"<amount>197</amount>\n" +
						"<rcv_time><![CDATA[2017-04-21 11:01:43]]></rcv_time>\n" +
					"</hbinfo>\n" +
				"</hblist>\n" +

				"</xml>";
		try {
			RedPackResultWrapper r= XmlObjectMapper.defaultMapper().fromXml(dd, RedPackResultWrapper.class);
			AppSettingMixin mixin = r.getAppSettingMixin();
			RedPackResult redPackResult = r.getRedPackResult();
			System.out.println(mixin.toString());
			System.out.println(redPackResult.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
