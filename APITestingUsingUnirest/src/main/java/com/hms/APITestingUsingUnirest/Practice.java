package com.hms.APITestingUsingUnirest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class Practice
{
	//@Test(priority=1)
	public void Wsauthcall()
	{
		System.out.println("hello wsauthcall");
		 {
			//System.out.println("priya");
			HttpResponse<JsonNode> response1 = null;
			try {
				response1 = Unirest.post("http://livestable.hx.local/ws/web/wsauth")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
						  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"wsauth\",\r\n      \"key\": \"FB89960E9272CBA804A40D342685D78430FFEBEA\"\r\n    }\r\n  }\r\n }")
						  .asJson();
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JsonNode body = response1.getBody();
			System.out.println(body);
			
			//converting to gson for extracting keys
			Gson gson = new Gson();
			
		    String responseJSONString = response1.getBody().toString();
		    System.out.println("check::"+responseJSONString);
		    JSONObject jsonResult = new JSONObject(responseJSONString);
		   // JSONArray data = jsonResult.getJSONArray("hotelogix");
		    /*if(data!=null)
		    {
		    	String[]  
		    }*/
		    System.out.println("getting accesskey from json object::="+jsonResult.getJSONObject("hotelogix").getJSONObject("response").getString("accesskey"));
		    
		    
		    
		    

		    
		    
		 }
	}
}
