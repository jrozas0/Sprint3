package bank;

public class AuthorizationRequest {
	private String creditCardNumber;
	private float amount;
	private String orderCode;
	
	public AuthorizationRequest(){
		super();
	}
	
	public AuthorizationRequest(String creditCardNumber, float amount,
			String orderCode) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.amount = amount;
		this.orderCode = orderCode;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}
