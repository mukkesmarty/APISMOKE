package com.hms.APITestingUsingUnirest;


import java.time.LocalDateTime;
import java.time.Year;

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


public class Gethoteldata
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Gethoteldatacall(String s)
	{
		keytype = s;
		System.out.println("keytype:"+s);
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in gethoteldata:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in gethoteldata:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			HttpResponse<JsonNode> responsegethoteldata = Unirest.post(""+serverurl+"/ws/web/gethoteldata")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("page", "1")
					  .header("offset", "20")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "26ad551a-1c2d-f65e-068d-cc152ac0e7cf")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"gethoteldata\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n            \"roomType\":true,\r\n            \"salutation\":true,\r\n            \"identityType\":true,\r\n            \"rate\":true,\r\n             \"businessSource\":true\r\n      }\r\n    }\r\n  }\r\n}\r\n")
					  .asJson();
			JsonNode body = responsegethoteldata.getBody();
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}//End of Gethoteldatacall() method
	
	public String extractingmessagegethoteldata()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String gethoteldatastring;
		gethoteldatastring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last gethoteldata success:"+gethoteldatastring);
		return gethoteldatastring;
	}//End of extractingmessagegethoteldata() method
}
