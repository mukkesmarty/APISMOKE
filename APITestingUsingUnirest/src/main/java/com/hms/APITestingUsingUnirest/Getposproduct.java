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

public class Getposproduct
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Getposproductcall(String s1)
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
			Getpos getposobj = new Getpos();
			getposobj.Getposcall(keytype);
			String posid = getposobj.extractingposid();
			 
			 
			 HttpResponse<JsonNode> responsegetposproduct = Unirest.post(""+serverurl+"/ws/web/getposproduct")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "94baad90-34a5-bf3a-7e68-46b5c38c2666")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getposproduct\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \"pos\": {\r\n          \"id\": \""+posid+"\"\r\n        }\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			 
			 JsonNode body = responsegetposproduct.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Getposproductcall() method
	 
	 public String extractingmessagegetposproduct()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getposproductstring;
			getposproductstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last getposproduct:"+getposproductstring);
			return getposproductstring;
	 }// End of extractingmessagegetposproduct() method
	 
}// End of class
