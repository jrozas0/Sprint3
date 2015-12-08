package lib.controllers;

import java.util.HashMap;

public class Bindabble extends HashMap<String, Action>{

	private static final long serialVersionUID = 1L;

	public void bind(String path, View view, RequestHandler handler) {
		super.put(path, new Action(view, handler));
	}
	
	public void bind(String path, RequestDelegator handler) {
		super.put(path, new Action(View.Delegator(), handler));
	}
		
}