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

public class Gethoteloptions
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	
	public void Gethoteloptionscall(String s1)
	{
		keytype=s1;
		
		if(keytype == "wsauth")
		{
			System.out.println("hello if gethoteloptions:");
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauth key in getallpaytypes"+ keyw);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in gethoteloptions:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
	
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in addtocart:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			System.out.println("welcome to gethoteloptions try block:");
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello accesskey in gethoteloptions:"+accesskey);
			
			HttpResponse<JsonNode> responsegethoteloptions = Unirest.post(""+serverurl+"/ws/web/gethoteloptions")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "c503c57b-c8e8-4205-4930-a13617eccb49")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"gethoteloptions\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"languagecode\": \"en\",\r\n      \"data\": {\r\n        \"roomType\": true,\r\n        \"rate\": true,\r\n        \"salutation\": true,\r\n        \"rateFor\": [\r\n          \"FDESK\",\r\n          \"CORP\",\r\n          \"AGENT\",\r\n          \"OTH\",\r\n          \"CENTRT\",\r\n          \"WEB\",\r\n          \"VIP\"\r\n        ],\r\n        \"identityType\": true,\r\n        \"maxpax\": true,\r\n        \"hotelLanguage\": true,\r\n        \"defaultCurrency\": true,\r\n        \"hotelCurrency\": true,\r\n        \"colorCodes\": true,\r\n        \"webSettings\": true,\r\n        \"webDesignCssStyles\": true,\r\n        \"webStaticText\": true\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responsegethoteloptions.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Gethoteloptionscall() method
	
	public String extractingmessagegethoteloptions()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String gethoteloptionsstring;
		gethoteloptionsstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last gethoteloptions:"+gethoteloptionsstring);
		return gethoteloptionsstring;
	}// End of extractingmessagegethoteloptions() method 
}// End of class
