package com.seucre.gateway.HttpSessionGateway.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.seucre.gateway.HttpSessionGateway.UserInfo;

@Component
public class AuthInterceptor implements HandlerInterceptor  {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(false);
		
		if(session !=null && session.getAttribute("authUser")!=null) {
			UserInfo user = (UserInfo) session.getAttribute("authUser");
			System.out.println(" Found authenticationn "+user.toString());
			request.setAttribute("p1", "raj");
			
			return true;
		} else {
			System.out.println("Can not find authenticationn ");
			throw new Exception();
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
