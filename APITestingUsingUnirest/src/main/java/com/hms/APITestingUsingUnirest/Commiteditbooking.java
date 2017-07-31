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

public class Commiteditbooking
{
	static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String bookingType;
	String accesskey;
	String editbookingtempid;
	
	public void Commiteditbookingcall(String s1)
	{
		keytype = s1;
		System.out.println("keytype:"+s1);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in addtocart:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in commiteditbooking:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcall("login");
			String editbookingtempid1 = editbookingobj.extractingtempid();
			editbookingtempid = editbookingtempid1;
			System.out.println("editbooking temp id in commiteditbooking"+editbookingtempid);
			
			HttpResponse<JsonNode> responsecommiteditbooking = Unirest.post(""+serverurl+"/ws/web/commiteditbooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "0ea7bfe0-9ade-e173-2351-5698405c5e2a")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"commiteditbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\":\"S\",\r\n         \"id\":\""+editbookingtempid+"\"\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsecommiteditbooking.getBody();
			System.out.println("commiteditbooking:"+body);
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}//End of Commiteditbookingcall() method
	
	
	
	public void Commiteditbookingcall(String s1,String s2, String s3)
	{
		keytype = s1;
		bookingType = s2;
		String restype = s3;
		
		System.out.println("keytype:"+s1);
		
		if(keytype == "wsauth")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in commiteditbooking:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcall("login","G",restype);
			//String editbookingtempid1 = editbookingobj.extractingtempid();
			
			String grouptempid = Getbookings.getGroupTempID;
			
			//editbookingtempid = editbookingtempid1;
			System.out.println("editbooking temp id in commiteditbooking"+editbookingtempid);
			
			HttpResponse<JsonNode> responsecommiteditbooking = Unirest.post(""+serverurl+"/ws/web/commiteditbooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "0ea7bfe0-9ade-e173-2351-5698405c5e2a")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"commiteditbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\":\""+bookingType+"\",\r\n         \"id\":\""+grouptempid+"\"\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsecommiteditbooking.getBody();
			System.out.println("commiteditbooking:"+body);
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}//End of Commiteditbookingcall() method
	
	
	
	
	
	
	public String extractingmessagecommiteditbooking()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String commitediteditbookingstring;
		commitediteditbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last commiteditbooking success :"+commitediteditbookingstring);
		return commitediteditbookingstring;
	}//End of extractingmessagecommiteditbooking() method
}
