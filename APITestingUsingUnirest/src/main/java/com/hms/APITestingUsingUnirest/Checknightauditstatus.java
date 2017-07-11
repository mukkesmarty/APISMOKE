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

public class Checknightauditstatus
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
	
	public void Checknightauditstatuscall(String s)
	{
		keytype = s;
		System.out.println("keytype:"+s);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauth key in getallpaytypes"+ keyw);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in checknightauditstatus:"+keyw);
			
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in checkingnightstatus:"+accesskey);
			accesskey = keyl;
		}
		
		try
		{
			
			serverurl = CommonConfig.serverurl;
			
			HttpResponse<JsonNode> responsechecknightauditstatus = Unirest.post(""+serverurl+"/ws/web/checknightauditstatus")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "3ffe20fc-472f-9fab-d525-50595ae84c80")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"checknightauditstatus\",\r\n      \"key\": \""+accesskey+"\"\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responsechecknightauditstatus.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Checknightauditstatuscall() method
	
	public String extractingmessagecheckingnightauditstatus()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String checkingnightauditstatusstring;
		checkingnightauditstatusstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last checkingnightauditstatus success:"+checkingnightauditstatusstring);
		
		int ln = checkingnightauditstatusstring.length();
		String partialmessage = checkingnightauditstatusstring.substring(0,ln-11);
		System.out.println(partialmessage);
		return partialmessage;
	} // End of extractingmessagecheckingnightauditstatus() method
}// End of class
