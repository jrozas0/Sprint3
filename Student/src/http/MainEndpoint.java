package http;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.StudentController;
import controllers.lib.Action;
import controllers.lib.Bindabble;

@WebServlet("/*")
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
		mappings.bind("/get", "courses.jsp", (req, res) -> StudentController.getCourses(req));
	}
	    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
		Action.Handle(mappings, config, request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action.Handle(mappings, config, request, response);
	}
	
}
