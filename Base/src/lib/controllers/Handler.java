package lib.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {

	public Object apply(HttpServletRequest request, HttpServletResponse response);
	
}
