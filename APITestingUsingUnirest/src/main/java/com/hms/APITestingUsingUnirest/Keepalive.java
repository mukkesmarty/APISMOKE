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


public class Keepalive
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String hotelid2;
	String keytype; 
	String accesskey;
	
	public void Keepalivecall(String s1)
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
			System.out.println("wsauthkey in keepalive:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
	
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in keepalive:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			HttpResponse<JsonNode> responsekeepalive = Unirest.post(""+serverurl+"/ws/web/keepalive")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "f54b8159-912a-48e7-0b1a-672d913af404")
					  .body(" {\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"keepalive\",\r\n      \"key\": \""+accesskey+"\"\r\n    }\r\n  }\r\n } ")
					  .asJson();
			JsonNode body = responsekeepalive.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of Keepalivecall() method 
	
	public String extractingmessagekeepalive()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String keepalivesuccessstring;
		keepalivesuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last keepalive success"+keepalivesuccessstring);
		return keepalivesuccessstring;
	}// extractingmessagekeepalive() method

}// End of class
