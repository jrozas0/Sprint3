package controllers;

import javax.servlet.http.HttpServletRequest;

import lib.controllers.Action;
import lib.controllers.View;
import lib.controllers.ex.BadRequest;
import lib.controllers.ex.NotFound;
import beans.User;
import beans.managers.UserManager;
import beans.Course;
import beans.managers.CourseManager;

public class AdminController {
		
 
    public static View deletecourse(HttpServletRequest req) {

                return View.FinateState("/views/deletecourse.jsp", "ok");
    }
    
    public static View deleteuser(HttpServletRequest req) {

        return View.FinateState("/views/deleteuser.jsp", "ok");
}
    
    
}
