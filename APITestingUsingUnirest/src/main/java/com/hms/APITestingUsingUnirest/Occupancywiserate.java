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

public class Occupancywiserate
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 String date1;
	 String date2;
	 
	 public void Occupancywiseratecall(String s1)
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
				System.out.println("wsauthkey in occupancywiserate:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in occupancywiserate:"+keyl);
				accesskey = keyl;
			}
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 date1 = CommonConfig.nightauditdate1;
			 date2 = CommonConfig.nightauditdate2;
			 
			 HttpResponse<JsonNode> responseoccupancywiserate = Unirest.post(""+serverurl+"/ws/web/occupancywiserate")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "1b8d728b-8a38-8d27-ba53-8966ed96a657")
					  .body("{\r\n    \"hotelogix\":{\r\n        \"version\":\"1.0\",\r\n        \"datetime\":\"2016-04-20T10:42:57\",\r\n        \"request\":{\r\n            \"method\":\"occupancywiserate\",\r\n            \"key\":\""+accesskey+"\",\r\n            \"languagecode\":\"en\",\r\n            \"data\":{\r\n                \"stay\":{\r\n                    \"checkindate\":\""+date1+"\",\r\n                    \"checkoutdate\":\""+date2+"\"\r\n                },\r\n                \"searchfor\":{\r\n                    \"value\":\"CORP\"\r\n                },\r\n                \"searchforid\":{\r\n                    \"value\":\"\"\r\n                }\r\n            }\r\n        }\r\n    }\r\n}")
					  .asJson(); 
			 
			 JsonNode body = responseoccupancywiserate.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Occupancywiseratecall() method
	 
	 public String extractingmessageoccupancywiserate()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String occupancywiseratestring;
			occupancywiseratestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last search occupancywiserate:"+occupancywiseratestring);
			return occupancywiseratestring;
	 }// End of extractingmessageoccupancywiserate() method 
}// End of class
