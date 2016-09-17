package com.bx.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @date 2016年4月8日 uuu.java
 * @author CZP
 * @parameter
 */
public class SecurityInterceptor implements HandlerInterceptor {
	// 这样以/login结尾的请求不做拦截处理。
	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = request.getRequestURI();
		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				return true;
			}
		}
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") == null) {
			throw new AuthorizationException();
		} else {
			return true;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
}
