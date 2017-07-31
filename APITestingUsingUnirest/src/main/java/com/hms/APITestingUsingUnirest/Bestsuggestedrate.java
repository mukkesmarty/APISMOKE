package com.hms.APITestingUsingUnirest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;

public class Bestsuggestedrate
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Bestsuggestedratecall(String s1)
	 {
		 keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if getcountrylist:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in bestsuggestedrate:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in bestsuggestedrate:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 String date1 = CommonConfig.nightauditdate;
			 String date2 = CommonConfig.nightauditdate1;
			 
			 System.out.println("hello:: accesskey in bestsuggestedrate:"+accesskey);
			 
			 HttpResponse<JsonNode> responsebestsuggestedrate = Unirest.post(""+serverurl+"/ws/web/bestsuggestedrate")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "4536f3fe-68f7-87f2-a236-c097a72907e1")
					  .body("{\r\n    \"hotelogix\":{\r\n        \"version\":\"1.0\",\r\n        \"datetime\":\"2016-04-20T10:42:57\",\r\n        \"request\":{\r\n            \"method\":\"bestsuggestedrate\",\r\n            \"key\":\""+accesskey+"\",\r\n            \"languagecode\":\"en\",\r\n            \"data\":{\r\n                \"stay\":{\r\n                    \"checkindate\":\""+date1+"\",\r\n                    \"checkoutdate\":\""+date2+"\"\r\n                },\r\n                \"pax\":{\r\n                    \"adult\":\"1\",\r\n                    \"child\":\"0\"\r\n                },\r\n                \"roomrequire\":{\r\n                    \"value\":\"1\"\r\n                }\r\n            }\r\n        }\r\n    }\r\n}")
					  .asJson();
			 
			 JsonNode body = responsebestsuggestedrate.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Bestsuggestedrate() method
	 
	 public String extractingmessagebestsuggetedrate()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String bestsuggestedratestring;
			bestsuggestedratestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last bestsuggestedrate:"+bestsuggestedratestring);
			return bestsuggestedratestring;
	 }// End of extractingmessagebestsuggetedrate() method 
}// End of class
