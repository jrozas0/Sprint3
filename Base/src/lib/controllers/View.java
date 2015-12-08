package lib.controllers;

import javax.servlet.http.HttpServletRequest;

public class View {

	private String path;
	private String state;

	private View(String path) {
		super();
		this.path = path;
	}
	
	private View(String path, String state) {
		super();
		this.state = state;
		this.path = path;
	}

	public String getState() {
		return state;
	}
	
	public boolean isFiniteState() {
		return state != null;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPath() {
		return path;
	}
	
	public static View Simple(String path) {
		return new View(path);
	}
	
	public static View FinateState(String path, String state) {
		return new View(path, state);
	}
	
	public static View Delegator() {
		return null;
	}
	
	public static boolean is(HttpServletRequest request, String state) {
		if (request.getAttribute("state") == null) return false;
		else return request.getAttribute("state").equals(state);
	}
		
	
}
