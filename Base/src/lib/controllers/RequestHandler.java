package lib.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.controllers.ex.MVCException;

public abstract class RequestHandler implements Handler {
	
	public abstract Object handle(HttpServletRequest request, HttpServletResponse response) throws MVCException;
		
	public static RequestHandler PLAIN() {
		return null;
	}

	@Override
	public Object apply(HttpServletRequest request, HttpServletResponse response) {
		return this.handle(request, response);
	}
	
}