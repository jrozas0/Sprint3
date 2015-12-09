package http;

import java.io.IOException;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.CourseController;
import controllers.UserController;
import lib.controllers.Action;
import lib.controllers.Bindabble;
import lib.controllers.RequestDelegator;
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
		
		//Basic navigation with no state. These are basic bindings for redirecting to static views
		//based on the path requested
		mappings.bind("/home", View.Simple("/views/plain/home.jsp"), RequestHandler.PLAIN());
		mappings.bind("/login", View.Simple("/views/login.jsp"), RequestHandler.PLAIN());
		mappings.bind("/register", View.Simple("/views/register.jsp"), RequestHandler.PLAIN());
		
		//a request handler is a class, which by calling it's only method, will apply the state returned
		//from it to the view. View which will be rendered using these attributes set in the request.
		mappings.bind("/profile", View.Simple("/views/profile.jsp"), new RequestHandler() {
			@Override
			public Object handle(HttpServletRequest req, HttpServletResponse res) throws MVCException {
				return UserController.userProfile(req);
			}
		});
		
		//a request delegator is a controller that is likewise executed on a certain path,
		//but instead of rendering a view based on it's returned values, will redirect to another view
		//this is specially useful if we want to treat views as finite state machines, which for instance
		//parameter validation will lead to it's state change and ence visual representation.
		//In this case, we are checking the user post request, and based on it's validity we add a 
		//specific state to the view in a dynamic way
		mappings.bind("/register/post", new RequestDelegator() {
			@Override
			public View delegate(HttpServletRequest req, HttpServletResponse res) throws MVCException {
				return UserController.register(req);
			}
		});
		
		mappings.bind("/login/post", new RequestDelegator() {
			@Override
			public View delegate(HttpServletRequest req, HttpServletResponse res) throws MVCException {
				return UserController.login(req);
			}
		});
		
		mappings.bind("/logout", View.FinateState("/views/plain/home.jsp", "loggedout"), new RequestHandler() {
			@Override
			public Object handle(HttpServletRequest request, HttpServletResponse response) throws MVCException {
				return UserController.logout(request);
			}
		});
		
		mappings.bind("/courses", View.Simple("/views/courses.jsp"), new RequestHandler() {
			@Override
			public Object handle(HttpServletRequest req, HttpServletResponse res) throws MVCException {
				return CourseController.coursesForCat(req);
			}
		});
		
		mappings.bind("/course/chat", new RequestDelegator() {
			@Override
			public View delegate(HttpServletRequest req, HttpServletResponse res) throws MVCException {
				try {
					return CourseController.sendChat(req);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
		
		mappings.bind("/course/chat/show", View.Simple("/views/chat.jsp"), RequestHandler.PLAIN());
		mappings.bind("/course/chat/get", new RequestDelegator() {
			@Override
			public View delegate(HttpServletRequest req, HttpServletResponse res) throws MVCException {
				try {
					return CourseController.readChat(req, res);
				} catch (IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
		
		mappings.bind("/courses/teacher", View.Simple("/views/teacher/newcourse.jsp"), RequestHandler.PLAIN());
		mappings.bind("/courses/teacher/post", new RequestDelegator() {
			@Override
			public View delegate(HttpServletRequest req, HttpServletResponse res) throws MVCException {
				return CourseController.newCourse(req);
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
