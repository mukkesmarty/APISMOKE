package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;


public class Editcorporate
{
	static String responseJSONString;
    String serverurl;
    String hotelid1;
    String keytype; 
    String accesskey;
    
    
	public void Editcorporatecall(String s)
	{
		System.out.println("welcome to editcorporate");
    	keytype = s;
    	System.out.println("check parameter::"+s);
    	
    	
    	if(keytype == "wsauth")
		{
    		/*System.out.println("this is wsauth");
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;*/
    		String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in addtocart:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*System.out.println("this is login");
			Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;*/
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in addtocart:"+keyl);
			accesskey = keyl;
		}
    	
    	try
    	{
    		System.out.println("welcome to try block");
    		//Getcounters objgetcounters = new Getcounters();
        	//objgetcounters.Getcounterscall();
        	//String counter = objgetcounters.extractingdefaultcountername();
        	//System.out.println("counter name:"+counter);
        	
        	//System.out.println("helllo again");
        	//System.out.println("hello::"+counter);
        	//System.out.println("welcome to try block1");
        	
        	//Getusertypes objgetusertypes = new Getusertypes();
        	//objgetusertypes.Getusertypescall();
        	//String usertypetitle = objgetusertypes.extractingusertypetitle();
        	//System.out.println("welcome to try block2");
        	
        	Getcorporates objgetcorporates = new Getcorporates();
        	objgetcorporates.Getcorporatescall(keytype);
        	String corporateid = objgetcorporates.extractingcorporateids();
    		
    		System.out.println("editcorporate inside try");
    		serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
		
			String uniqueID = UUID.randomUUID().toString();
			String uniqueID1 = uniqueID+"@hotelogix.com";
			
			
			//String email = uniqueID+"hotelogix.com";
			String str1 = uniqueID.substring(0,9);
			System.out.println("substring"+str1);
			
			// Get the current date and time
			LocalDateTime currentTime = LocalDateTime.now();
			System.out.println("Current DateTime: " + currentTime);
			int year = Year.now().getValue()+1;
			System.out.println(year);
			
			System.out.println("after first scection in try block");
    		
    		HttpResponse<JsonNode> responseeditcorporate = Unirest.post(""+serverurl+"/ws/web/editcorporate")
    				  .header("content-type", "application/json")
    				  .header("x-ig-sg", "D_gg%fkl85_j")
    				  .header("cache-control", "no-cache")
    				  .header("postman-token", "180b1f6f-e28d-3fdc-c2b3-4775d9fea881")
    				  .body("{\r\n    \"hotelogix\": {\r\n      \"version\": \"1.0\",\r\n      \"datetime\": \"2012-01-16T10:11:15\",\r\n      \"request\": {\r\n        \"method\": \"editcorporate\",\r\n        \"key\": \""+accesskey+"\",\r\n        \"data\": {\r\n          \"groupCode\": \""+str1+"\",\r\n          \"businessName\": \""+uniqueID+"\",\r\n          \"addresses\": {\r\n            \"office\": {\r\n              \"address\": \"Bihar\",\r\n              \"country\": \"India\",\r\n              \"state\": \"delhi\",\r\n              \"city\": \"delhi\",\r\n              \"zip\": \"123\"\r\n            },\r\n            \"billing\": {\r\n              \"companyName\": \""+uniqueID+"\",\r\n              \"address\": \"abcd\",\r\n              \"country\": \"Pakistan\",\r\n              \"state\": \"Punjab\",\r\n              \"city\": \"delhi\",\r\n              \"zip\": \"123\"\r\n            }\r\n          },\r\n          \"contacts\": {\r\n            \"personal\": {\r\n              \"salutation\": \"Mr\",\r\n              \"fName\": \"Mukesh\",\r\n              \"lName\": \"kumar\",\r\n              \"designation\": \"Manager\",\r\n              \"phoneNo\": \"8802640811\",\r\n              \"phoneExtension\": \"101\",\r\n              \"faxNo\": \"101\",\r\n              \"email\": \""+uniqueID1+"\",\r\n              \"mobileNo\": \"8802640811\",\r\n              \"gender\": \"M\",\r\n              \"dob\": \"2016-03-01\",\r\n              \"website\": \"www.hotelogix.com\"\r\n            },\r\n            \"billing\": {\r\n              \"salutation\": \"Mr\",\r\n              \"fName\": \"Mohan\",\r\n              \"lName\": \"Mishra\",\r\n              \"designation\": \"Manager\",\r\n              \"phoneNo\": \"8802640811\",\r\n              \"phoneExtension\": \"101\",\r\n              \"faxNo\": \"101\",\r\n              \"email\": \""+uniqueID1+"\",\r\n              \"mobileNo\": \"123\",\r\n              \"gender\": \"M\",\r\n              \"dob\": \"2016-03-01\",\r\n              \"website\": \"www.yahoo.com\"\r\n            }\r\n          },\r\n          \"creditCardDetail\": {\r\n            \"nameOnCard\": \"Mohan\",\r\n            \"cardNo\": \"4111111111111111\",\r\n            \"cardType\": \"Visa\",\r\n            \"expiryMonth\": \"05\",\r\n            \"expiryYear\": \""+year+"\",\r\n            \"cvc\": \"101\",\r\n            \"address\": \"creditCardAddress\",\r\n            \"country\": \"India\",\r\n            \"state\": \"Delhi\",\r\n            \"city\": \"Delhi\",\r\n            \"zip\": \"20160301\"\r\n          },\r\n          \"creditLimit\": \"100\",\r\n          \"paymentTerms\": \"5\",\r\n          \"discountable\": true,\r\n          \"discount\": \"5.2\",\r\n          \"hotels\": [\r\n            \r\n            \r\n            {\r\n              \"id\": \""+hotelid1+"\",\r\n              \"corporateIds\": [\r\n                \""+corporateid+"\"\r\n              ]\r\n            }\r\n          ]\r\n        }\r\n      }\r\n    }\r\n  }")
    				  .asJson();
    		JsonNode body = responseeditcorporate.getBody();
    		System.out.println("editcorporate response::"+body);
    		responseJSONString = body.toString();
    	}// End of try block
    	
    	catch(UnirestException e)
    	{
    		e.printStackTrace();
    	}// end of catch block
	}// end of Editcorporatecall() method
	
	public String extractingmessageeditcorporate()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String editcorporatesuccessstring;
		editcorporatesuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last editcorporate success:"+editcorporatesuccessstring);
		return editcorporatesuccessstring;
	}// end of extractingmessageeditcorporate() method
}
