package controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student/*")
public class StudentManager extends HttpServlet {       	

	private static final long serialVersionUID = 1L;

	private Bindabble mapping;
			
	private ServletConfig config;
	
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		super.init(config);
		bindPaths();
	}
	
	private void bindPaths() {
		mapping = new Bindabble();
		mapping.bind("/get", "studentCourses.jsp", (req, res) -> StudentController.getCourses(req));
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
		Action action = mapping.get(request.getServletPath());
		request.setAttribute("in", action.handler.handle(request, response));
		config.getServletContext().getRequestDispatcher(action.view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
