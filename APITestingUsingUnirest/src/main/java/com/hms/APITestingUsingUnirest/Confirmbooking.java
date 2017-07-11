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

public class Confirmbooking
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Confirmbookingcall(String s1)
	 {
		 
		 keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in confirmbooking:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in confirmbooking:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to confirmbooking try block:");
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello accesskey in confirmbooking:"+accesskey);
			 Savebooking savebookingobj = new Savebooking();
			 savebookingobj.Savebookingcall(keytype);
			 String orderid = savebookingobj.extractingorderid();
			 System.out.println("orderid in confirmbooking"+orderid);
			 String deposittotal = savebookingobj.extractingdeposittotal();
			 System.out.println("deposit total in confirmbooking"+deposittotal);
			 
			 HttpResponse<JsonNode> responseconfirmbooking = Unirest.post(""+serverurl+"/ws/web/confirmbooking")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "58e24a66-d02d-4398-5490-23f8af38a960")
					  .body("{\r\n    \"hotelogix\": {\r\n         \"version\": \"1.0\",\r\n         \"datetime\": \"2012-01-16T10:10:15\",\r\n         \"request\": {\r\n             \"method\": \"confirmbooking\",\r\n             \"key\": \""+accesskey+"\",\r\n             \"languagecode\": \"en\",\r\n             \"data\": {\r\n                \"payment\": {\r\n                  \"amount\": \""+deposittotal+"\",\r\n                  \"isguestcc\": \"0\",\r\n                  \"type\": \"CC\",\r\n                  \"description\": \"Test Payment\"\r\n                },\r\n                \"creditcard\": {\r\n                  \"nameoncard\": \"Test Dev\",\r\n                  \"cardno\": \"4111111111111111\",\r\n                  \"cardtype\": \"visa\",\r\n                  \"cvc\": \"156\",\r\n                  \"expirymonth\": \"12\",\r\n                  \"expiryyear\": \"2019\"\r\n                },\r\n                \"orderId\": {\r\n                  \"value\": \""+orderid+"\"\r\n                }\r\n             }\r\n\r\n         }\r\n    }\r\n }")
					  .asJson();
			 
			 JsonNode body = responseconfirmbooking.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Confirmbookingcall() method
	 
	 public String extractingmessageconfirmbooking()
	 {
		 	String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String confirmbookingstring;
			confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last confirmbooking::"+confirmbookingstring);
			return confirmbookingstring;
	 }// End of extractingmessageconfirmbooking() method
	
}// End of class
