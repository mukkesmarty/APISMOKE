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

public class Getdnrs
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String hotelid2;
	String keytype; 
	String accesskey;
	
	public void Getdnrscall(String s)
	{
		keytype = s;
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauth key in getallpaytypes"+ keyw);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in getdnrs:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
	
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getdnrs:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			String date1 = CommonConfig.nightauditdate1;
			String date2 = CommonConfig.nightauditdate2;
			
			HttpResponse<JsonNode> responsegetdnrs = Unirest.post(""+serverurl+"/ws/web/getdnrs")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "67b47db6-c806-c26c-0e23-f63248b8d033")
					  .body("{\r\n \"hotelogix\": {\r\n  \"version\": \"1.0\",\r\n   \"datetime\": \"2012-01-16T10:10:15\",\r\n   \"request\": {\r\n     \"method\": \"getdnrs\",\r\n     \"key\": \""+accesskey+"\",\r\n     \"data\": {\r\n        \"fromDate\": \""+date1+"\",\r\n        \"toDate\": \""+date2+"\"\r\n     }\r\n   }\r\n }\r\n}")
					  .asJson();
			JsonNode body = responsegetdnrs.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Getdnrscall() method
	
	public String extractingmessagegetdnrs()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getdnrssuccessstring;
		getdnrssuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last getdnrs success::"+getdnrssuccessstring);
		return getdnrssuccessstring;
	}// End of extractingmessagegetdnrs() method
	
	public String extractingdnrid()
	{
		String localresponseJSONString = responseJSONString;
		String dnridstring ="hi";
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray dnrsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("dnrs");
		dnridstring = dnrsArray.getJSONObject(0).get("id").toString();
		System.out.println("getdnr dnrid:"+dnridstring);
		return dnridstring;
	}// End of extractingdnrid() method 
}// End of class
