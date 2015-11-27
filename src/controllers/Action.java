package controllers;

public class Action {

	public String view;
	public RequestHandler handler;
	
	public Action(String view, RequestHandler handler) {
		this.view = view;
		this.handler = handler;
	}
	
}
