package com.hzmc.weixin.admin.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.hzmc.weixin.admin.base.BaseController;
import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.WxRedpackTemplet;
import com.hzmc.weixin.admin.dao.model.WxRedpackTempletExample;
import com.hzmc.weixin.admin.service.WxRedpackTempletService;
import com.hzmc.weixin.admin.util.validator.NotNullValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * WxRedpackTempletcontroller
 * Created by wph on 2017/5/8.
 */
@RestController
@RequestMapping("/WxRedpackTemplet")
@Api(value = "微信红包活动模板控制器", description = "微信红包活动模板管理")
public class WxRedpackTempletController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(WxRedpackTempletController.class);

	@Autowired
	private WxRedpackTempletService wxRedpackTempletService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "添加活动模板")
	private Object addRedpackTemplet(@RequestBody WxRedpackTemplet wxRedpackTemplet) {
		ComplexResult result = FluentValidator.checkAll()
				.on(wxRedpackTemplet.getActName(), new NotNullValidator("活动名称"))
				.on(wxRedpackTemplet.getSendName(), new NotNullValidator("商户名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new Result(ResultConstant.FAILED, result.getErrors());
		}
	/*	String[] mins = wxRedpackTemplet.getMinAmount().split(".");
		String minposition = mins[1];
		String[] maxs = wxRedpackTemplet.getMaxAmount().split(".");
		String maxposition = maxs[1];
		if (minposition.length() > 2) {
			return new Result(ResultConstant.FAILED, "最小金额最多二位小数点");
		} else if (maxposition.length() > 2) {
			return new Result(ResultConstant.FAILED, "最大金额最多二位小数点");
		}*/

		Double min = Double.valueOf(wxRedpackTemplet.getMinAmount()) * 100;
		BigDecimal bmin = new BigDecimal(min);
		min = bmin.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		Double max = Double.valueOf(wxRedpackTemplet.getMaxAmount()) * 100;
		BigDecimal bmax = new BigDecimal(max);
		max = bmax.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		wxRedpackTemplet.setTotalAmount(wxRedpackTemplet.getTotalAmount() * 100);
		wxRedpackTemplet.setMinAmount(min.intValue() + "");
		wxRedpackTemplet.setMaxAmount(max.intValue() + "");
		List<WxRedpackTemplet> wxRedpackTempletList = wxRedpackTempletService.getTempletByActName(wxRedpackTemplet.getActName());
		if (wxRedpackTempletList.size() > 0) {
			return new Result(ResultConstant.FAILED, "活动名称已经存在");
		}
		int count = wxRedpackTempletService.insert(wxRedpackTemplet);
		return new Result(ResultConstant.SUCCESS, wxRedpackTemplet.getId());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据Id查找红包模板记录")
	private Object getRedPackTemPletList(@PathVariable Integer id) {
		WxRedpackTemplet wxRedpackTemplet = wxRedpackTempletService.selectByPrimaryKey(id);
		return new Result(ResultConstant.SUCCESS, wxRedpackTemplet);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "得到所有的模板")
	private Object getRedPackTemPletLists() {
		WxRedpackTempletExample wxRedpackTemplet = new WxRedpackTempletExample();
		List<WxRedpackTemplet> wxRedpackTemplets = wxRedpackTempletService.selectByExample(wxRedpackTemplet);
		return new Result(ResultConstant.SUCCESS, wxRedpackTemplets);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据Id删除红包模板记录")
	private Object deleteRedPackTemPletList(@PathVariable Integer id) {
		int count = wxRedpackTempletService.deleteByPrimaryKey(id);
		if (count == 1) {
			return new Result(ResultConstant.SUCCESS, count);
		} else {
			return new Result(ResultConstant.FAILED, count);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ApiOperation(value = "更新红包模板记录")
	private Object updateRedPackTemPletList(@RequestBody WxRedpackTemplet wxRedpackTemplet) {
		Double min = Double.valueOf(wxRedpackTemplet.getMinAmount()) * 100;
		BigDecimal bmin = new BigDecimal(min);
		min = bmin.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		Double max = Double.valueOf(wxRedpackTemplet.getMaxAmount()) * 100;
		BigDecimal bmax = new BigDecimal(max);
		max = bmax.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		wxRedpackTemplet.setTotalAmount(wxRedpackTemplet.getTotalAmount() * 100);
		wxRedpackTemplet.setMinAmount(min.intValue() + "");
		wxRedpackTemplet.setMaxAmount(max.intValue() + "");
		int count = wxRedpackTempletService.updateByPrimaryKey(wxRedpackTemplet);
		if (count == 1) {
			return new Result(ResultConstant.SUCCESS, count);
		} else {
			return new Result(ResultConstant.FAILED, count);
		}
	}
}