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

public class Getzonelist
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Getzonelistcall(String s1)
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
				System.out.println("wsauthkey in getzonelist:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getzonelist:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello:: accesskey in getzonelist:"+accesskey);
			 
			 HttpResponse<JsonNode> responsegetzonelist = Unirest.post(""+serverurl+"/ws/web/getzonelist")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "700c9868-a34c-43df-887c-fe3a686c592d")
					  .body("{\r\n        \"hotelogix\": {\r\n          \"version\": \"1.0\",\r\n          \"datetime\": \"2012-01-16T10:10:15\",\r\n          \"request\": {\r\n            \"method\": \"getzonelist\",\r\n            \"key\": \""+accesskey+"\",\r\n            \"languagecode\": \"en\",\r\n            \"data\": {\r\n              \"countryCode\": {\r\n                \"value\": \"US\"\r\n              }\r\n            }\r\n\r\n          }\r\n        }\r\n      }")
					  .asJson();
			 
			 JsonNode body = responsegetzonelist.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	 }// End of Getzonelistcall() method
	 
	 public String extractingmessagegetzonelist()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getzoneliststring;
			getzoneliststring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last getzonelist:"+getzoneliststring);
			return getzoneliststring;
	 }// End of extractingmessgaegetzonelist() method 
}// End of class
