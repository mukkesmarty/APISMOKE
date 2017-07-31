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



public class Login extends Getcounters
{
	//Getcounters objgetcounter = new Getcounters();

	static String responseJSONString;
	static String finalloginaccesskey;
	String serverurl;
	String hotelid1;
	String key;
	String emailid;
	String password;
	
	
	//@Test(priority= 9)
	public void Logincall()
	{
		Getcounters objgetcounter = new Getcounters();
		objgetcounter.Getcounterscall();
		
	//	objgetcounter.Getcounterscall();
		//objgetcounter.extractingmessagegetcounters();
		
		System.out.println("hello login");
		
		//String sc=super.defaultcounter;
		String defaultcounter = objgetcounter.extractingdefaultcounterid();
		//System.out.println(defaultcounter);
		
		
		//objwsauth.Wsauthcall();
		//objwsauth.extractingWsauthKey();
		//String d2=super.cfinalstring;
		//System.out.println("cfinalstring in login class::" +cfinalstring);
		System.out.println("getcounter value in Logincall::"+defaultcounter);
		
		try {
			serverurl= CommonConfig.serverurl;
			key=CommonConfig.key;
			hotelid1=CommonConfig.hotelid1;
			emailid=CommonConfig.emailid;
			password=CommonConfig.password;
			
			HttpResponse<JsonNode> responseLogin = Unirest.post(""+serverurl+"/ws/web/login")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "6e60e3ae-4be7-3a77-ea2f-4a61a21d5eaa")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"login\",\r\n      \"key\": \""+key+"\",\r\n      \"data\": {\r\n         \"hotelId\":"+hotelid1+",\r\n         \"counterId\":\""+defaultcounter+"\",\r\n         \"email\":\""+emailid+"\",\r\n         \"password\":\""+password+"\",\r\n         \"forceOpenCouner\":true,\r\n         \"forceLogin\":true\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			
			JsonNode body = responseLogin.getBody();
			System.out.println(body);
			
			//converting to gson for extracting keys
			Gson gson = new Gson();
		    responseJSONString = responseLogin.getBody().toString();
		    System.out.println("check login::"+responseJSONString);
			
			
		} 
		catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test(priority = 10)
	public String extractingmessagelogin()
	{
		
		System.out.println(responseJSONString);
		String[] s1=responseJSONString.split("message",0);
		System.out.println("hello string login");
		System.out.println(s1[1]);
		String straftersplit =s1[1];
		System.out.println(straftersplit);
		String finalstring = straftersplit.substring(3, 10);
		System.out.println(finalstring);
		String outputstring = "success";
		
		Assert.assertEquals(finalstring, outputstring);
		String successstring = outputstring;
		return successstring;
	}
	
	public String extractingLoginKey()
	{
		System.out.println("Hello extractingLoginKey method");
		String localresponseJSONString = responseJSONString;
		//now creating json object
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		//System.out.println("getting accesskey from json object::="+jsonResult.getJSONObject("hotelogix").getJSONObject("response").getString("accesskey"));
		
		String loginaccesskey;
		loginaccesskey = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getString("accesskey");
		System.out.println("login access key::"+loginaccesskey);
		finalloginaccesskey = loginaccesskey;
		System.out.println("login access key in login"+finalloginaccesskey);
		return loginaccesskey;
		
	}
}
