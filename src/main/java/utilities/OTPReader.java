package utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OTPReader {
	
	public static void main(String args[]) { 
		OTPReader reader = new OTPReader();
		reader.otpReaderUtility();
	} 
	
	public String otpReaderUtility() {
		RestAssured.baseURI = "https://api.twilio.com/2010-04-01/Accounts/ACc3104b3eedcee501f784298f87045af4/Messages.json"; 
		RequestSpecification request = RestAssured.given(); 
		request.auth().basic("ACc3104b3eedcee501f784298f87045af4", ""); 
		Response response = request.get(); 
 
		//System.out.println(response.asString()); 
		JsonPath myPath = new JsonPath(response.asString()); 
		String body = myPath.getString("messages[0].body"); 
		System.out.println(body); 
		String otp=body.substring(0, 6); 
		System.out.println(otp); 
		return otp;
	}

}
