package controllers.lib.ex;

public abstract class MVCException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public abstract Error getError();

}