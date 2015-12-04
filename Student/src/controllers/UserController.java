package controllers;

import javax.servlet.http.HttpServletRequest;

import beans.User;
import beans.managers.UserManager;


public class UserController {

	public static boolean isTeacher(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		//find out if the user is a teacher or not
		User user = UserManager.get(id).get();
			
			if (user.getType().compareTo("Teacher") == 0){
				return true;
			
			} else {
				return false;
			}
	}
	
	public static boolean isLoggedIn(User user, HttpServletRequest request) {
		return request.getSession().getAttribute("userId").equals(user.getId());
	}

	
}
