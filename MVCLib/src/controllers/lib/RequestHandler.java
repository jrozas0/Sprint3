package controllers.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.lib.ex.MVCException;

@FunctionalInterface
public interface RequestHandler {
	
	public abstract Object handle(HttpServletRequest request, HttpServletResponse response) throws MVCException;
		
}