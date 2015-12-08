package controllers;

import javax.servlet.http.HttpServletRequest;

import lib.controllers.Action;
import lib.controllers.View;
import lib.controllers.ex.BadRequest;
import lib.controllers.ex.NotFound;
import beans.User;
import beans.managers.UserManager;

public class UserController {
	
	//simple helper to be called on the view
	public static boolean isLoggedIn(User user, HttpServletRequest request) {
		return request.getSession().getAttribute("userId").equals(user.getId());
	}
	
	public static User getFromSession(HttpServletRequest request) {
		return UserManager.getById((Long)(request.getSession().getAttribute("userId"))).get(); 
	}
		
	//handle register from post
	public static View register(HttpServletRequest request) {
		//TODO
		return View.FinateState("/views/register.jsp", "ok");
	}
	
    public static View login(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (Action.validate(req, email, password)) {
            if (UserManager.validate(email, password)) {
                User user = UserManager.getByEmail(email).get();
                req.getSession().setAttribute("user", user);
                return View.FinateState("views/login.jsp", "ok");
            } else {
            	return View.FinateState("/views/login.jsp", "notvalid");
            }
        } else throw new BadRequest();
    }
    
    public static User userProfile(HttpServletRequest req) {
		Long id;
		try {
			 id = Long.parseLong(req.getParameter("id"));
		} catch(NumberFormatException e) {
			throw new BadRequest();
		}
		User user = UserManager.getById(id).get();
		if (user == null) throw new NotFound();
		else return user;
    }
    	
}
