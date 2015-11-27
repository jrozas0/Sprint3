package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface RequestHandler {
	
	public abstract Object handle(HttpServletRequest request, HttpServletResponse response);
		
}
