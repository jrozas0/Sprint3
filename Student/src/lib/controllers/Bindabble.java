package lib.controllers;

import java.util.HashMap;

public class Bindabble extends HashMap<String, Action>{

	private static final long serialVersionUID = 1L;

	public void bind(String path, String view, RequestHandler handler) {
		super.put(path, new Action(view, handler));
	}
		
}