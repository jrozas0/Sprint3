package lib.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.controllers.ex.MVCException;
import lib.controllers.ex.NotFound;

public class Action {

	public View view;
	public Handler handler;
	
	public Action(View view, Handler handler) {
		this.view = view;
		this.handler = handler;
	}
	
	private static void Apply(Bindabble mappings, ServletConfig config, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MVCException {
		Action action = mappings.get(request.getPathInfo());
		if (action == null) throw new NotFound();
		Object state = null;
		if (action.handler != null && !(state instanceof View))
			state = action.handler.apply(request, response);
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
	
    //helper for validating inputs
    public static boolean validate(HttpServletRequest req, Object... input) {
        for(int i = 0; i < input.length; i++) {
            if (input[i] == null)  {
                return false;
            }
        }
        return true;
    }	

			
}