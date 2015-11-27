package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Course;
import beans.User;
import beans.managers.UserManager;

public class StudentController {
		
	public static List<Course> getCourses(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = UserManager.get(id).get();
		return user.getCourses();
	}
		
}
