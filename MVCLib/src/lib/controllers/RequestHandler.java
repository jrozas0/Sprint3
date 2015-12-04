package lib.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.controllers.ex.MVCException;

@FunctionalInterface
public interface RequestHandler {
	
	public abstract Object handle(HttpServletRequest request, HttpServletResponse response) throws MVCException;
		
}