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

public class Bookingrestrictions
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 String date1;
	 String date2;
	 
	 public void Bookingrestrictionscall(String s1)
	 {
		 keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello if getposproduct:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in getposproduct:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getposproduct:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 date1 = CommonConfig.nightauditdate1;
			 date2 = CommonConfig.nightauditdate2;
			 
			 HttpResponse<JsonNode> responsebookingrestrictions = Unirest.post(""+serverurl+"/ws/web/bookingrestrictions")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "458872c0-da19-e3d6-84ba-b7b93ba761bc")
					  .body("{\r\n    \"hotelogix\": {\r\n      \"version\": \"1.0\",\r\n      \"datetime\": \"2012-01-16T10:10:15\",\r\n      \"request\": {\r\n        \"method\": \"bookingrestrictions\",\r\n        \"key\": \""+accesskey+"\",\r\n        \"languagecode\": \"en\",\r\n        \"data\": {\r\n          \"daterange\": {\r\n            \"startdate\":\""+date1+"\",\r\n            \"enddate\":\""+date2+"\"\r\n          }\r\n        }\r\n\r\n      }\r\n    }\r\n}")
					  .asJson();
			 
			 JsonNode body = responsebookingrestrictions.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Bookingrestrictions() method
	 
	 public String extractingmessagebookingrestrictions()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String bookingrestrictionsstring;
			bookingrestrictionsstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last search bookingrestrictions:"+bookingrestrictionsstring);
			return bookingrestrictionsstring;
	 }// End of extractingmessagebookingrestrictions() method 
}// End of class
