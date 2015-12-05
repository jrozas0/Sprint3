package controllers;

import javax.servlet.http.HttpServletRequest;

import beans.User;
import beans.managers.UserManager;


public class UserController {
	
	public static boolean isLoggedIn(User user, HttpServletRequest request) {
		return request.getSession().getAttribute("userId").equals(user.getId());
	}

	
}
