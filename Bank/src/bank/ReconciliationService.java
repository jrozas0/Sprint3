package bank;


import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.sql.PreparedStatement;

import java.sql.Connection;

@Path("/bankService")
public class ReconciliationService {

	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public ReconciliationService() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("jdbc/bankDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@POST
	@Path("/supplierUpdate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SupplierAccountUpdateResponse updateSupplierAccount(
			SupplierAccountUpdateRequest request) {
		int supplierID = request.getSupplierID();
		double amount = request.getAmount();
		System.out.println("amount " + amount);
		int month = request.getMonth();
		int year = request.getYear();
		int dateValue = year * 100 + month;
		System.out.println("HERE");
		boolean failed = false;
		try {

			Connection conn = ds.getConnection();
			System.out.println(conn);
			PreparedStatement preparedStatement;
			String sql = "INSERT INTO SUPPLIERRECONCILIATION VALUES(?,?,?)";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, dateValue);
			preparedStatement.setDouble(2, amount);
			preparedStatement.setInt(3, supplierID);
			preparedStatement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			failed = true;
		}
		SupplierAccountUpdateResponse returnValue = new SupplierAccountUpdateResponse();
		if (!failed) {
			returnValue.setOperationCode("" + dateValue);
		} else {
			returnValue.setOperationCode("error");
		}
		return returnValue;
	}

	@POST
	@Path("/businessReconciliation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BusinessReconciliationResponse doBusinessReconciliation(
			BusinessReconciliationRequest request) {
		double amount = request.getAmount();
		System.out.println("amount " + amount);
		int month = request.getMonth();
		int year = request.getYear();
		int dateValue = year * 100 + month;
		boolean failed = false;
		try {
			Connection conn = ds.getConnection();
			System.out.println(conn);
			PreparedStatement preparedStatement;
			String sql = "INSERT INTO BUSINESSRECONCILIATION VALUES(?,?)";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, dateValue);
			preparedStatement.setDouble(2, amount);
			preparedStatement.executeUpdate();

			conn.close();

		} catch (SQLException e) {
			failed = true;
		}
		BusinessReconciliationResponse returnValue = new BusinessReconciliationResponse();
		if (!failed) {
			returnValue.setOperationCode("" + dateValue);
		} else {
			returnValue.setOperationCode("error");
		}
		return returnValue;
	}

}