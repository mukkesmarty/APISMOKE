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

public class Logout
{
	static String responseJSONString;
	String serverurl;
	//@Test(priority=1)
	public void Logoutcall()
	{
		System.out.println("Hello logout call");
		//Login objlogin = new Login();
		//objlogin.Logincall();
		//String loginaccesskey = objlogin.extractingLoginKey();
		//String loginaccesskey = objlogin.extractingLoginKey();
		
		String loginaccesskey = Login.finalloginaccesskey;
		System.out.println("in logout, login access key"+loginaccesskey);
		
		try {
			serverurl= CommonConfig.serverurl;
			
			HttpResponse<JsonNode> response1 = Unirest.post(""+serverurl+"/ws/web/logout")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ba848db4-5abb-f7b9-1840-3e5b4e214770")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"logout\",\r\n      \"key\": \""+loginaccesskey+"\"\r\n    }\r\n  }\r\n }")
					  .asJson();
			System.out.println(response1);
			responseJSONString = response1.getBody().toString();
		    }
		
		catch (UnirestException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // end of Logoutcall method
	
	//@Test(priority=2)
	public String extractingmessageLogout()
	{
		System.out.println("hello extractingmessageLogout");
		System.out.println(responseJSONString);
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String logoutsuccessstring;
		logoutsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last"+logoutsuccessstring);
		return logoutsuccessstring; 
		
		
		//return null;
	}
	
	
}
