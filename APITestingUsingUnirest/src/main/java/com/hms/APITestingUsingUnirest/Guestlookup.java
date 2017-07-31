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

public class Guestlookup
{
	static String responseJSONString;
	static String finalloginaccesskey;
	String serverurl;
	String hotelid1;
	String key;
	String emailid;
	String password;
	String keytype;
	String accesskey;
	
	public void Guestlookupcall(String s)
	{
		keytype = s;
		System.out.println("keytype:"+keytype);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauth key in getallpaytypes"+ keyw);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in guestlookup:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			accesskey = keyl;
			System.out.println("login key in guestlookup:"+accesskey);
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			HttpResponse<JsonNode> responseguestlookup = Unirest.post(""+serverurl+"/ws/web/guestlookup")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "cc4f8696-57ed-001f-e9c8-c9ada2ff89a9")
					  .body("{\r\n          \"hotelogix\": {\r\n            \"version\": \"1.0\",\r\n            \"datetime\": \"2012-01-16T10:10:15\",\r\n            \"request\": {\r\n              \"method\": \"guestlookup\",\r\n              \"key\": \""+accesskey+"\",\r\n              \"data\": {\r\n                 \"lookupText\":\"chamcham\",\r\n                 \"searchByCompany\": true\r\n              }\r\n            }\r\n          }\r\n       }")
					  .asJson();
			
			JsonNode body = responseguestlookup.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Guestlookupcall() method 
	
	public String extractingmessageguestlookup()
	{
		System.out.println("welcome to extractingmessageguestlookup");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String guestlookupstring;
		guestlookupstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last guestlookup success:"+guestlookupstring);
		return guestlookupstring;
	}
	
}// End of class
