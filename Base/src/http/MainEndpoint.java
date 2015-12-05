package http;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.StudentController;
import lib.controllers.Action;
import lib.controllers.Bindabble;
import lib.controllers.RequestHandler;
import lib.controllers.ex.MVCException;

@WebServlet("/Main/*")
public class MainEndpoint extends HttpServlet {       	

	private static final long serialVersionUID = 1L;

	private Bindabble mappings;
			
	private ServletConfig config;
	
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		super.init(config);
		bindPaths();
	}
	
	private void bindPaths() {
		mappings = new Bindabble();
		
		mappings.bind("/", "/views/plain/home.jsp", RequestHandler.PLAIN());
		
		mappings.bind("/courses", "/views/courses.jsp", new RequestHandler() {
			@Override
			public Object handle(HttpServletRequest request, HttpServletResponse response) throws MVCException {
				return StudentController.getCourses(request);
			}
		});
	}
	    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
		Action.Handle(mappings, config, request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action.Handle(mappings, config, request, response);
	}
	
}
