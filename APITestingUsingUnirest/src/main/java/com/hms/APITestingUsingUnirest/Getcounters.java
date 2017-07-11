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

public class Getcounters extends Wsauth
{
	//Wsauth objwsauthg = new Wsauth();
	 String defaultcounter;
	 String serverurl;
	 String hotelid1;
	
	
	
	//@Test(priority = 6)
	public void Getcounterscall() 
	{
		Wsauth objwsauth = new Wsauth();
		objwsauth.Wsauthcall();
		System.out.println("hello getcounters");
		objwsauth.Wsauthcall();
		String wsauthkey = objwsauth.extractingWsauthKey();
		
		//String d2=super.wsauthkeyfinalstring;
		//String d3=super.this.wsauthkeyfinalstring;
		System.out.println("wsauth key in getcounter ::"+ wsauthkey);
		System.out.println(wsauthkeyfinalstring);
		
		
		
		try {
			serverurl= CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			
			HttpResponse<JsonNode> responseGetcounters = Unirest.post(""+serverurl+"/ws/web/getcounters")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ce2e448f-ad27-5878-261a-0c66dd6617dd")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:11:15\",\r\n    \"request\": {\r\n      \"method\": \"getcounters\",\r\n      \"key\": \""+wsauthkey+"\",\r\n      \"data\": {\r\n        \"hotels\": [\r\n          {\r\n            \"id\": \""+hotelid1+"\"\r\n          }\r\n        ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responseGetcounters.getBody();
			System.out.println(body);
			
			//converting to gson for extracting keys
			Gson gson = new Gson();
		    responseJSONString = responseGetcounters.getBody().toString();
		    System.out.println("check getcounters::"+responseJSONString);
			
		    }
		
		catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	//@Test(priority=7)
	public String extractingmessagegetcounters()
	{
		System.out.println(responseJSONString);
		String[] s1=responseJSONString.split("message",0);
		System.out.println("hello string getcounters");
		System.out.println(s1[1]);
		String straftersplit =s1[1];
		System.out.println(straftersplit);
		String finalstring = straftersplit.substring(3, 10);
		System.out.println(finalstring);
		String outputstring = "success";
		
		Assert.assertEquals(finalstring, outputstring);
		return finalstring;
	}
	
	//@Test(priority=8)
	public String extractingdefaultcounterid()
	{
		System.out.println("hello extractingdefaultcounter");
		System.out.println(responseJSONString);
		String[] s1 = responseJSONString.split("Default Counter",0);
		System.out.println(s1[1]);
		String[] s2 = s1[1].split("users",0);
		System.out.println(s2[0]);
		int l = s2[0].length();
		String finalstring = s2[0].substring(8,l-3);
		System.out.println(finalstring);
		defaultcounter=finalstring;
		String getcountersdefaultcounter = defaultcounter;
		System.out.println("counter"+getcountersdefaultcounter);
		return getcountersdefaultcounter;
	}
	
	public String extractingdefaultcountername()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray countersArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels").getJSONObject(0).getJSONArray("counters"); 
		JSONObject countername = countersArray.getJSONObject(0);
		String cname = countername.getString("name");
		String getcounternamestring = cname;
		return getcounternamestring;
	}
	
}
