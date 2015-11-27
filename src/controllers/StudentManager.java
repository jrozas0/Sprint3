package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student/*")
public class StudentManager extends HttpServlet {       	

	private static final long serialVersionUID = 1L;

	private Map<String, RequestHandler> pageMapping;
			
	private ServletConfig config;
	
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		super.init(config);
		bindPaths();
	}
	
	private void bindPaths() {
		pageMapping = new HashMap<String, RequestHandler>();
		pageMapping.put("/get", (req, res) -> StudentController.getCourses(req));
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
		RequestHandler handler = pageMapping.get(request.getServletPath());
		request.setAttribute("in", handler.handle(request, response));
		config.getServletContext().getRequestDispatcher("").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
