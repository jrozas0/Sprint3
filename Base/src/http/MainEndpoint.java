package http;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.StudentController;
import controllers.UserController;
import lib.controllers.Action;
import lib.controllers.Bindabble;
import lib.controllers.RequestHandler;
import lib.controllers.View;
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
		
		//basic navigation with no state
		mappings.bind("/home", View.Simple("/views/plain/home.jsp"), RequestHandler.PLAIN());
		mappings.bind("/login", View.Simple("/views/login.jsp"), RequestHandler.PLAIN());
		mappings.bind("/register", View.Simple("/views/register.jsp"), RequestHandler.PLAIN());
		
		mappings.bind("/login/post", View.Delegator(), new RequestHandler() {
			@Override
			public Object handle(HttpServletRequest request, HttpServletResponse response) throws MVCException {
				return UserController.login(request);
			}
		});
						
		mappings.bind("/courses", View.Simple("/views/courses.jsp"), new RequestHandler() {
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
