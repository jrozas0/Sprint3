package controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import beans.Category;
import beans.managers.CategoryManager;
import lib.controllers.ex.BadRequest;

public class CourseController {

	public static Optional<Category> coursesForCat(HttpServletRequest req) {
		Long id;
		try {
			 id = Long.parseLong(req.getParameter("category"));
		} catch(NumberFormatException e) {
			throw new BadRequest();
		}
		return Optional.ofNullable(CategoryManager.getById(id));
	}
	
}
