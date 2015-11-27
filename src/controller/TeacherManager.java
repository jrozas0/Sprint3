package controller;

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

/**
 * Servlet implementation class TeacherHandler
 */
@WebServlet("/TeacherHandler")
public class TeacherManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private Map<String, RequestHandler> pageMap;
	//add pages
	private String[] navigationPages = {""}; 
	
    public TeacherManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		mapPages();
	}
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		if (path != null) {
			RequestHandler handler = pageMap.get(path);
			if (handler != null) {
				String view = handler.handleRequest(request, response);
				if (view != null) {
					RequestDispatcher rdp = request.getRequestDispatcher(view);
					rdp.forward(request, response);
				}
			}else{
				//error
				}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String path = request.getServletPath();
			
			if (path != null) {
				RequestHandler handler = pageMap.get(path);
				if (handler != null) {
					String view = handler.handleRequest(request, response);
					if (view != null) {
						RequestDispatcher rdp = request.getRequestDispatcher(view);
						rdp.forward(request, response);
					}
				}else{
					//error
					}
			}
	}
	
	private void mapPages() {
		pageMap = new HashMap<String, RequestHandler>();
		for (String page : navigationPages) {
			pageMap.put(page, new TeacherHelper());
		}
	}

}