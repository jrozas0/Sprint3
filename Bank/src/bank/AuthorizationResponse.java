package bank;

public class AuthorizationResponse {
	private String response;
	private String operationCode;
	
	public AuthorizationResponse(){
		super();
	}
	public AuthorizationResponse(String response, String operationCode) {
		super();
		this.response = response;
		this.operationCode = operationCode;
	}
	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	/**
	 * @return the operationCode
	 */
	public String getOperationCode() {
		return operationCode;
	}
	/**
	 * @param operationCode the operationCode to set
	 */
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	
}
