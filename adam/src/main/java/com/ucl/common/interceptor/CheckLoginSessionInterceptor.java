package com.ucl.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLoginSessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getSession().getAttribute("sessMbrSeq") != null) {
			// by pass
		} else {
			if(request.getSession().getAttribute("sessWorker") == "usr") {
				response.sendRedirect("/loginUsr");
			} else if(request.getSession().getAttribute("sessWorker") == "sdm") {
				response.sendRedirect("/loginSdm");	
			}
			
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
