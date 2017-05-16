package com.hzmc.weixin.admin.base;

import com.hzmc.weixin.admin.constant.ResultConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 */
public abstract class BaseController {

	private final static Logger _log = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 统一异常处理
	 *
	 * @param request
	 * @param response
	 * @param exception
	 */
	@ExceptionHandler
	public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		_log.error("统一异常处理：", exception);
		return new Result(ResultConstant.FAILED, "异常");
	}


}
