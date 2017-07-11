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

public class Changepayterm
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
	
	public void Changepaytermcall(String s1)
	{
		keytype = s1;
		System.out.println("keytype:"+s1);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauthkey in changepayterm:"+ accesskey);*/
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
			System.out.println("login key in changepayterm:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			
			serverurl = CommonConfig.serverurl;
			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall(keytype);
			//String groupcode = getbookingsobj.extractinggroupcode();
			String groupmainid = getbookingsobj.extractinggroupmainid();
			System.out.println("group main id in changepayterm:"+groupmainid);
			
			HttpResponse<JsonNode> responsechangepayterm = Unirest.post(""+serverurl+"/ws/web/changepayterm")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "0c9dcd1a-36b2-21ac-eb24-ac4c461666fb")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"changepayterm\",\r\n      \"key\": \""+accesskey+"\",\r\n       \"data\": {\r\n            \"groupId\":\""+groupmainid+"\",\r\n            \"term\":2\r\n        }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsechangepayterm.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Changepaytermcall() method 
	
	public String extractingmessagechangepayterm()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String changepaytermstring;
		changepaytermstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last changepayterm success:"+changepaytermstring);
		return changepaytermstring;
	}// End of extractingmessagechangepayterm() 
}// End of class
