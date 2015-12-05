package lib.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.controllers.ex.MVCException;

public interface RequestHandler {
	
	public abstract Object handle(HttpServletRequest request, HttpServletResponse response) throws MVCException;
		
	public static RequestHandler PLAIN() {
		return null;
	}
	
}