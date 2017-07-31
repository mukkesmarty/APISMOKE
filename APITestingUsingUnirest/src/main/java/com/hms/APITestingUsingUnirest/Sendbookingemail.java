package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;

public class Sendbookingemail
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Senbookingemailcall(String s1)
	{
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello if getposproduct:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in sendbookingemail:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in sendbookingemail:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall(keytype);
				String mainid = getbookingsobj.extractingmainid();
				System.out.println("main id of booking fetching from getbookings :"+mainid);
				
				HttpResponse<JsonNode> responsesendbookingemail = Unirest.post(""+serverurl+"/ws/web/sendbookingemail")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"sendbookingemail\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"languagecode\": \"en\",\r\n      \"data\": {\r\n          \"bookingId\":\""+mainid+"\",\r\n          \"type\":\"S\",\r\n          \"name\": \"mukesh test automation\",\r\n          \"message\":\"test description for API automation\",\r\n          \"email\": \"mukesh.kumar@hotelogix.com\"\r\n      }\r\n    }\r\n  }\r\n}")
						  .asJson();
				
				JsonNode body = responsesendbookingemail.getBody();
				responseJSONString = body.toString();
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		
		
	}// Senbookingemailcall() method
	
	public String extractingmessagesendbookingemail()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String sendbookingemailstring;
		sendbookingemailstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last sendbookingemail success::"+sendbookingemailstring);
		return sendbookingemailstring;
	}//End of extractingmessagesendbookingemail() method
}// End of class
