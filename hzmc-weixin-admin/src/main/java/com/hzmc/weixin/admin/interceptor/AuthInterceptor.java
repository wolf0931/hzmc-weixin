package com.hzmc.weixin.admin.interceptor;

import com.hzmc.weixin.admin.base.Result;
import com.hzmc.weixin.admin.constant.ResultConstant;
import com.hzmc.weixin.admin.dao.model.User;
import com.hzmc.weixin.admin.util.UserSessionManger;
import com.hzmc.weixin.common.util.JsonMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wph on 2017/5/16.
 */
@Configurable
public class AuthInterceptor implements HandlerInterceptor {
	private static Logger LOGGER = Logger.getLogger(ApplicationEventListener.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean authFlag = true;
		String contextPath = request.getContextPath();
		String requestUrl = request.getRequestURI().replace(contextPath, "");
		System.out.println(requestUrl);
		if (!requestUrl.endsWith("/")) {
			requestUrl = requestUrl + "/";
		}
		String uri = request.getRequestURI();
		if (uri.contains("v2") || uri.contains("swagger")) {
			//接口列表
			return true;
		}
		if ("/access".equals(uri) ||
				"/login".equals(uri) ||
				"/vote".equals(uri) ||
				"/account/info".equals(uri) ||
				uri.contains("/wx/pay/") ||
				"/error".equals(uri)) {
			return true;
		}
		User user = null;
		if (handler instanceof HandlerMethod) {
			String sessionId = request.getSession().getId();
			if (sessionId == null) {
				return authFlag;
			} else {
				user = UserSessionManger.get(sessionId);
			}
			if (user == null) {
				authFlag = false;
				Result result = new Result(ResultConstant.FAILED, "用户没有登录");
				String jsonString = JsonMapper.defaultMapper().toJson(result);
				addHeadAndPrintWriteMsg(response, jsonString, 401);
				return authFlag;
			}
		}
		return authFlag;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}

	private void addHeadAndPrintWriteMsg(HttpServletResponse response,
										 String errorMsg, int status) throws IOException {

		if (response != null && !StringUtils.isEmpty(errorMsg)) {
			response.addHeader("Access-Control-Allow-Headers",
					"Origin, No-Cache, X-Requested-With, If-Modified-Since, "
							+ "Pragma, Last-Modified, Cache-Control,"
							+ " Expires, Content-Type, X-E4M-With");
			response.setStatus(status);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(errorMsg);
			out.flush();
			out.close();
		}
	}
}
