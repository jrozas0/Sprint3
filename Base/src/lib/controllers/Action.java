package lib.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.controllers.ex.MVCException;

public class Action {

	public String view;
	public RequestHandler handler;
	
	public Action(String view, RequestHandler handler) {
		this.view = view;
		this.handler = handler;
	}
	
	private static void Apply(Bindabble mappings, ServletConfig config, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MVCException {
		System.out.println("got hit!");
		System.out.println(request.getPathInfo());
		Action action = mappings.get(request.getPathInfo());
		request.setAttribute("in", action.handler.handle(request, response)); //may throw runtime exception here from the controllers
		config.getServletContext().getRequestDispatcher(action.view).forward(request, response);
	}
	
	private static void sendError(lib.controllers.ex.Error error, ServletConfig config, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("in", error);
		config.getServletContext().getRequestDispatcher("/views/plain/error.jsp").forward(request, response);
	}
	
	public static void Handle(Bindabble mappings, ServletConfig config, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Action.Apply(mappings, config, request, response);
		} catch (MVCException e) { //catching the runtime exception thrown by the controllers
			Action.sendError(e.getError(), config, request, response);
		}
	}
			
}