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

public class Deletednrs
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String hotelid2;
	String keytype; 
	String accesskey;
	
	public void Deletednrscall(String s1)
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
			System.out.println("wsauthkey in deletednrs:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
	
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in deletednrs:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			System.out.println("hello try block deletednrs::");
			
			Getdnrs getdnrsobj = new Getdnrs();
			System.out.println("hi delete");
			getdnrsobj.Getdnrscall(keytype);
			
			
			String dnrid = getdnrsobj.extractingdnrid();
			System.out.println("hello delete");
			System.out.println("dnrid in deletednr:"+dnrid);
			
			HttpResponse<JsonNode> responsedeletednrs = Unirest.post(""+serverurl+"/ws/web/deletednrs")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "8f22390c-0549-2314-ec6a-719b1ad7bcf1")
					  .body("{\r\n \"hotelogix\": {\r\n  \"version\": \"1.0\",\r\n   \"datetime\": \"2012-01-16T10:10:15\",\r\n   \"request\": {\r\n     \"method\": \"deletednrs\",\r\n     \"key\": \""+accesskey+"\",\r\n     \"data\": {\r\n        \"dnrs\":[\r\n            {\r\n               \"id\":\""+dnrid+"\"\r\n            }\r\n           ]\r\n     }\r\n   }\r\n }\r\n}")
					  .asJson();
						
			JsonNode body = responsedeletednrs.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of Deletednrscall() method 
	public String extractingmessagedeletednrs()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String deletednrssuccessstring;
		deletednrssuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last deletednrs success"+deletednrssuccessstring);
		return deletednrssuccessstring;
	}// End of extractingmessagedeletednrs() method 
}// End of class
