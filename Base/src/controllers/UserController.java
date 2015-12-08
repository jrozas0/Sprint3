package controllers;

import javax.servlet.http.HttpServletRequest;

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
	
    private static View login(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (validate(req, email, password)) {
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
    
    //helper for validating inputs
    public static boolean validate(HttpServletRequest req, Object... input) {
        for(int i = 0; i < input.length; i++) {
            if (input[i] == null)  {
                return false;
            }
        }
        return true;
    }
	
	
}
