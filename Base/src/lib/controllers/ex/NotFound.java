package lib.controllers.ex;

public class NotFound extends MVCException {

	@Override
	public Error getError() {
		return Error.Build(404, "Page not found.");
	}

}
