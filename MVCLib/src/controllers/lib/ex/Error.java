package controllers.lib.ex;

public class Error {

	private int error;
	private String message;
	
	public static Error Build(int error, String message) {
		return new Error(error, message);
	}
	
	public Error(int error, String message) {
		this.error = error;
		this.message = message;
	}

	public int getCode() {
		return error;
	}

	public String getMessage() {
		return message;
	}
	
}
