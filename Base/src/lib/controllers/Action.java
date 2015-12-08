package lib.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.controllers.ex.MVCException;

public class Action {

	public View view;
	public RequestHandler handler;
	
	public Action(View view, RequestHandler handler) {
		this.view = view;
		this.handler = handler;
	}
	
	private static void Apply(Bindabble mappings, ServletConfig config, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MVCException {
		Action action = mappings.get(request.getPathInfo());
		Object state = null;
		if (action.handler != null && !(state instanceof View))
			state = action.handler.handle(request, response);
			request.setAttribute("in", state); //may throw runtime exception here from the controllers
		if (state != null && (state instanceof View)) {
			View view = ((View)state);
			request.setAttribute("state", view.getState());
			config.getServletContext().getRequestDispatcher(view.getPath()).forward(request, response);
			return;
		} else {
			config.getServletContext().getRequestDispatcher(action.view.getPath()).forward(request, response);
		}
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