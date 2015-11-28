package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controllers.lib.ex.BadRequest;
import controllers.lib.ex.MVCException;
import beans.Course;
import beans.User;
import beans.managers.UserManager;

public class StudentController {
		
	public static List<Course> getCourses(HttpServletRequest request) throws MVCException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		if (id == null) throw new BadRequest();
		User user = UserManager.get(id).get();
		return user.getCourses();
	}
		
}
