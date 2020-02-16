package com.seucre.gateway.HttpSessionGateway.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.seucre.gateway.HttpSessionGateway.UserInfo;

@Component
public class AuthInterceptor implements HandlerInterceptor  {

	@Autowired
	private JwtUtil jwtUtil;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(false);
		
		if(session !=null && session.getAttribute("authUser")!=null) {
			UserInfo user = (UserInfo) session.getAttribute("authUser");
			// TODO validate token as well
			
			System.out.println(" Found authenticationn "+user.toString());
			request.setAttribute("p1", "raj");
			//token will be created in login controller and sent back to client and they need to send in further requests 
			// Yogesh :- below generation is only for the test purpose
			String token =jwtUtil.generateToken(user);
			System.out.println("token is ->>>>  "+token);
			// this token will be set as Authorization token for all other resource servers, in real world it will come in request header
			session.setAttribute("jwtToken", token);
			
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
