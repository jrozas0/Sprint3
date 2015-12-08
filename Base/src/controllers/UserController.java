package controllers;

import javax.servlet.http.HttpServletRequest;

import lib.controllers.View;
import lib.controllers.ex.BadRequest;
import beans.User;
import beans.managers.UserManager;

public class UserController {
	
	//simple helper to be called on the view
	public static boolean isLoggedIn(User user, HttpServletRequest request) {
		return request.getSession().getAttribute("userId").equals(user.getId());
	}
		
	//handle register from post
	public static View register(HttpServletRequest request) {
		Integer id;
		try {
			 id = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException e) {
			throw new BadRequest();
		}
		if (id == null) throw new BadRequest();
		
		User user = UserManager.get(id).get();
		return View.FinateState("/views/register.jsp", "ok");

	}
	
	
}
