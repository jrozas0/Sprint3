package controllers.lib.ex;

public class BadRequest extends MVCException {

	private static final long serialVersionUID = 1L;

	@Override
	public Error getError() {
		return Error.Build(500, "Values not valid. Please go back and retry.");
	}

}