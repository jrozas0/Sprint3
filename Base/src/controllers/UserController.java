package controllers;

import javax.servlet.http.HttpServletRequest;

import lib.controllers.View;
import beans.User;

public class UserController {
	
	public static boolean isLoggedIn(User user, HttpServletRequest request) {
		return request.getSession().getAttribute("user").equals(user.getId());
	}
	
	public static View login(HttpServletRequest request) {
		//get data from request, throw exception if input is not valid. which will redirect to error page
		//if credentials are valid, return a finitstate view with teh status:
		return View.FinateState("/views/register.jsp", "ok");
	}

	
}
