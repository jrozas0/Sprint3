package lib.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.controllers.ex.MVCException;

public abstract class RequestDelegator implements Handler {
	
	public abstract View delegate(HttpServletRequest request, HttpServletResponse response) throws MVCException;
	
	@Override
	public Object apply(HttpServletRequest request, HttpServletResponse response) {
		return this.delegate(request, response);
	}
	
}
