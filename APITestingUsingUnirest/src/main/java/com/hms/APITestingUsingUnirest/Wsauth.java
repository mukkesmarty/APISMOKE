package com.hms.APITestingUsingUnirest;

import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;


public class Wsauth
{
	 static String responseJSONString;
	static String wsauthkeyfinalstring;
	String serverurl;
	String key;
	
	//@Test(priority=2)
	 public void Wsauthcall()
	{
		System.out.println("hello wsauthcall");
		try {
			//System.out.println("priya");
			serverurl= CommonConfig.serverurl;
			System.out.println("rajni kant::"+serverurl);
			key = CommonConfig.key;
			System.out.println("rajni kant key ::"+key);
			
			HttpResponse<JsonNode> response1 = Unirest.post(""+serverurl+"/ws/web/wsauth")
					//.routeParam("baseurl", serverurl)
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"wsauth\",\r\n      \"key\": \""+key+"\"\r\n    }\r\n  }\r\n }")
					  .asJson();
			
			JsonNode body = response1.getBody();
			System.out.println(body);
			
			//converting to gson for extracting keys
			Gson gson = new Gson();
		    responseJSONString = response1.getBody().toString();
		    System.out.println("check::"+responseJSONString);
		    
		}
		
		catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("i m in catch");
		}
		
		
		
		
		/*public void extractingmessage()
		{
			String[] s1=responseJSONString.split("message",0);
			System.out.println("hello string");
			System.out.println(s1[1]);
			String straftersplit =s1[1];
			System.out.println(straftersplit);
			String finalstring = straftersplit.substring(3, 10);
			System.out.println(finalstring);
			String outputstring = "success";
			if(finalstring.equals(outputstring) )
			{
				System.out.println("testcase passed:: success found");
				
			}
		
		}*/		
		
	}
	 
	//@Test(priority=3)
	public String extractingmessage()
	{
		//Wsauth Wsauthobj = new Wsauth();
		//Wsauthobj.Wsauthcall();
		
		
		// TODO Auto-generated method stub
		System.out.println("hi");
		System.out.println(responseJSONString);
		//System.out.println(Wsauthobj.responseJSONString);
		 //
		String[] s1=responseJSONString.split("message",0);
		System.out.println("hello string");
		System.out.println(s1[1]);
		String straftersplit =s1[1];
		System.out.println(straftersplit);
		String finalstring = straftersplit.substring(3, 10);
		System.out.println(finalstring);
		String outputstring = "success";
		
		Assert.assertEquals(finalstring, outputstring);	
		return outputstring;
	}
	
	//@Test(priority=4)
	public String extractingWsauthKey()
	{
		
		System.out.println("hello wsauthkey");
		System.out.println(responseJSONString);
		String[] s1=responseJSONString.split("accesskey", 0);
		System.out.println(s1[1]);
		String[] s2=s1[1].split("status",0);
		System.out.println(s2[0]);
		int l= s2[0].length();
		System.out.println(l);
		String straftersplit= s2[0];
	    String finalstring= straftersplit.substring(3,l-3);
		System.out.println(finalstring);
		wsauthkeyfinalstring=finalstring;
		String wsauthkeystring = wsauthkeyfinalstring;
		return wsauthkeystring;
		
	}
	
	//@Test(priority = 5)
	public void disp()
	{
		System.out.println("i am disp function");
		System.out.println(wsauthkeyfinalstring);
	}
		
	
}


