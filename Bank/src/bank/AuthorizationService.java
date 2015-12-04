package bank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authorization")
public class AuthorizationService {
	
	@POST
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AuthorizationResponse authorization(AuthorizationRequest authorization){
		if(authorization.getCreditCardNumber().startsWith("2")){
			Calendar cal = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmSSSa");
	    	String code = "BANK"+dateFormat.format(cal.getTime());
	    	System.out.println("CODE "+code);
	    	AuthorizationResponse response = new AuthorizationResponse(code, "code");
	    	System.out.println(response.getOperationCode());
			return response;
		} else {
			return new AuthorizationResponse("error", "Reject");
		}
	}
}
