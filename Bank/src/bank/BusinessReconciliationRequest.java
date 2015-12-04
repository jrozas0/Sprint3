package bank;

public class BusinessReconciliationRequest {
	
	int month;
	int year;
	double amount;
	
	public BusinessReconciliationRequest(){
		
	}
	
	
	public BusinessReconciliationRequest(int month, int year, double amount) {
		super();
		this.month = month;
		this.year = year;
		this.amount = amount;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
