package com.hzmc.weixin.mp;

import com.hzmc.weixin.mp.user.Tags;
import com.hzmc.weixin.mp.user.bean.Tag;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @borball on 5/8/2016.
 */
public class TagsTest {

	@Test
	public void testList() {
		List<Tag> list = Tags.defaultTags().list();
		Assert.assertNotNull(list);
	}

}
