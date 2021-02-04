package com.mds.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			Object obj = session.getAttribute("member");
			if(obj != null) 
				return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/");
		return false;
	}
}
