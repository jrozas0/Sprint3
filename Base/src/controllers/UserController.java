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
		return (request.getSession().getAttribute("userId") != null) 
				&& request.getSession().getAttribute("userId").equals(user.getId());
	}
	
	public static User getFromSession(HttpServletRequest request) {
		return UserManager.getById((Long)(request.getSession().getAttribute("userId"))).get(); 
	}
	
	public static Object logout(HttpServletRequest request) {
		request.getSession().removeAttribute("userId");
		return Action.onlySideEffects();
	}
		
    public static View register(HttpServletRequest req) {
	        String nickname = null;
	        String name = null;
	        String surname = null;
	        String email = null;
	        String password = null;
	        Integer age = 0;
	        String description = null;
	        String address = null;
	        Integer phone = null;
	        String paymentData = null;
	        String teacherInput = null;
	        boolean teacher = false;
	        try {
	            //getting data from input
	            nickname = req.getParameter("nickname");
	            name = req.getParameter("name");
	            surname = req.getParameter("surname");
	            email = req.getParameter("email");
	            password = req.getParameter("password");
	            age = Integer.parseInt(req.getParameter("age"));
	            description = req.getParameter("description");
	            address = req.getParameter("address");
	            phone = Integer.parseInt(req.getParameter("phone"));
	            paymentData = req.getParameter("paymentData");
	            teacherInput = req.getParameter("teacher");
	            if (teacherInput != null)
	                teacher = true;
	        } catch(NullPointerException | NumberFormatException e) {
	            //if data from input is not valid, show warning in view
	        	return View.FinateState("/views/register.jsp", "notvalid");
	        }
	        if (Action.validate(req, nickname, name, surname, email, password, age, description, address, phone, paymentData, teacher)) {
	            if (UserManager.getByEmail(email).isPresent()) {
	            	//user already exists in the db
	            	return View.FinateState("/views/login.jsp", "alreadyexists");
	            } else {
	                //if the actual email of the user does not exist in the db, then we will register the user
                	User user = new User();
                    user.setAddress(address);
                    user.setAge(age);
                    user.setDescription(description);
                    user.setEmail(email);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setNick(nickname);
                    user.setPassword(password);
                    user.setPaymentData(paymentData);
                    user.setPhone(phone);
                    if (teacher) {
                    	user.setType("teacher");
                    } else {
                    	user.setType("student");
                    }
                    UserManager.save(user);
	                //update the state
                    return View.FinateState("/views/register.jsp", "ok");
	            }
	        } else {
	        	throw new BadRequest();
	        }
    }	
	        
    public static View login(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (Action.validate(req, email, password)) {
            if (UserManager.validate(email, password)) {
                User user = UserManager.getByEmail(email).get();
                req.getSession().setAttribute("userId", user.getId());
                return View.FinateState("/views/login.jsp", "ok");
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
