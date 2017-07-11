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

public class Sendinvoiceemail
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Sendinvoiceemailcall(String s1)
	{
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello if sendinvoiceemail:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in sendinvoiceemail:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in sendinvoiceemail:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				
			 HttpResponse<JsonNode> responsesendinvoiceemail = Unirest.post(""+serverurl+"/ws/web/sendinvoiceemail")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "0190e7db-adae-6d84-065a-ef776b855f23")
					  .body("{\r\n \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n   \"datetime\": \"2012-01-16T10:10:15\",\r\n   \"request\": {\r\n     \"method\": \"sendinvoiceemail\",\r\n     \"key\": \"|PhyzByoUMRjLVg\",\r\n     \"languagecode\": \"en\",\r\n     \"data\": {\r\n                \"name\": \"Rajesh kumar\",\r\n                \"email\": \"mukesh.kumar@hotelogix.com\",\t\r\n                \"message\":\"This is content of message\", \r\n                \"mailToFolioOwner\":true,\t\t \t\t\r\n                \"invoices\":[\r\n                       {\r\n                             \"id\":\"LjGM-NtP8w||\"\r\n                       }\r\n                ]\r\n             }\r\n         }\r\n     }\r\n }")
					  .asJson();
			 JsonNode body = responsesendinvoiceemail.getBody();
			 responseJSONString = body.toString();
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	}// End of Sendinvoiceemailcall() method
	
	public String extractingmessagesendinvoiceemail()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String sendinvoiceemailstring;
		sendinvoiceemailstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last sendinvoiceemail success::"+sendinvoiceemailstring);
		return sendinvoiceemailstring;
	}// End of extractingmessagesendinvoiceemail() method 
}// End of class
