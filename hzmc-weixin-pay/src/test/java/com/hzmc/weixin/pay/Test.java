package com.hzmc.weixin.pay;

import com.hzmc.weixin.common.util.XmlObjectMapper;

import java.io.IOException;

/**
 * Created by wph on 2017/4/24.
 */
public class Test {

	@org.junit.Test
	public void test1(){
		String xml = "<xml>\n" +
				"<name><![CDATA[wph]]></name>\n" +
				"<age><![CDATA[12]]></age>\n" +
				"<sudents>\n" +
					"<sude>\n" +
						"<name><![CDATA[wph12]]></name>\n" +
						"<sex><![CDATA[1]]></sex>\n" +
					"</sude>\n" +
					"<sude>\n" +
						"<name><![CDATA[wph13]]></name>\n" +
						"<sex><![CDATA[0]]></sex>\n" +
					"</sude>\n" +
				"</sudents>\n" +
				"</xml>";
		try {
			Person person = XmlObjectMapper.defaultMapper().fromXml(xml, Person.class);
			System.out.print(person.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
