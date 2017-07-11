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



public class Getagents
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Getagentscall(String s)
	{
		keytype = s;
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in addtocart:"+keyw);
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in addtocart:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			
			HttpResponse<JsonNode> responsegetagents  = Unirest.post(""+serverurl+"/ws/web/getagents")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "c483df3c-fc07-c930-99df-400e6b8eaf32")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:11:15\",\r\n    \"request\": {\r\n      \"method\": \"getagents\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n          \"hotels\": [\r\n            {\"id\": \""+hotelid1+"\"}\r\n          ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsegetagents.getBody();
			System.out.println("getagents reponse::"+body);
			responseJSONString = body.toString(); 
			
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}	
	}// End of Getagentscall method
	
	public String extractingmessagegetagents()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getagentssuccessstring;
		getagentssuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last getusers success"+getagentssuccessstring);
		return getagentssuccessstring; 
	}// End of extractingmessagegetagents method
	
	
	
}
