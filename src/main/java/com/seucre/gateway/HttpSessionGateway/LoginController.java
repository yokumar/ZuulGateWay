package com.seucre.gateway.HttpSessionGateway;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	// need to be changed as post method
	@GetMapping("/login")
	public boolean login(HttpServletRequest request) {
		//Implement logic to authenticate and set authenticated user object in session , setting 
		
		UserInfo user = new UserInfo("yogesh", "admin");
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser",user);
		return true;
		
	}
	
   @GetMapping("/getShop")
   public String getShop() {
	   return "jolly-store";
   }

   @GetMapping("/logout")
	public boolean logout(HttpServletRequest request) {
		//Implement logic to authenticate and set authenticated user object in session , setting 
		
		HttpSession session = request.getSession(false);
		session.invalidate();
		return true;
		
	}
	
}
