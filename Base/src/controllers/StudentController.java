package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lib.controllers.ex.BadRequest;
import lib.controllers.ex.MVCException;
import beans.Course;
import beans.User;
import beans.managers.UserManager;

public class StudentController {
		
	public static List<Course> getCourses(HttpServletRequest request) throws MVCException {
		Integer id;
		try {
			 id = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException e) {
			throw new BadRequest();
		}
		User user = UserManager.get(id).get();
		return user.getCourses();
	}	
	
	
}
