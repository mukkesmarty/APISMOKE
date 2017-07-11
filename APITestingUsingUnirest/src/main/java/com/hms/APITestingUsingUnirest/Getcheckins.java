package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

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

public class Getcheckins
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate;
	
	public void Getcheckinscall(String s)
	{
		keytype = s;
		System.out.println("keytype:"+s);
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
			System.out.println("login key in addtocart:"+keyl);
			accesskey = keyl;
		}
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate = CommonConfig.nightauditdate;
			
			HttpResponse<JsonNode> responsegetcheckins = Unirest.post(""+serverurl+"/ws/web/getcheckins")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{  \r\n   \"hotelogix\":{  \r\n      \"version\":\"1.0\",\r\n      \"datetime\":\"2012-01-16T10:10:15\",\r\n      \"request\":{  \r\n         \"method\":\"getcheckins\",\r\n         \"key\":\""+accesskey+"\",\r\n         \"data\":{  \r\n            \"date\":\""+nightauditdate+"\"\r\n         }\r\n      }\r\n   }\r\n}\r\n")
					  .asJson();
			JsonNode body = responsegetcheckins.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Getcheckinscall() method
	
	public String extractingmessagegetcheckins()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getcheckinsstring;
		getcheckinsstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getcheckins success:"+getcheckinsstring);
		return getcheckinsstring;
	}
}
