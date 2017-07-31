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


public class Editdnrs
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String hotelid2;
	String keytype; 
	String accesskey;
	
	public void Editdnrcall(String s1)
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
			System.out.println("wsauthkey in editdnrs:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
	
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in editdnrs:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			String date = CommonConfig.nightauditdate1;
			String date1 = CommonConfig.nightauditdate2;
			
			Getdnrs getdnrsobj = new Getdnrs();
			getdnrsobj.Getdnrscall(keytype);
			String dnrid = getdnrsobj.extractingdnrid();
			System.out.println("dnrid in editdnrs"+dnrid);
			
			HttpResponse<JsonNode> responseeditdnrs = Unirest.post(""+serverurl+"/ws/web/editdnrs")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "7c3c2546-9364-1c75-2615-4292dd473b13")
					  .body("{\r\n   \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n     \"datetime\": \"2012-01-16T10:10:15\",\r\n     \"request\": {\r\n       \"method\": \"editdnrs\",\r\n       \"key\": \""+accesskey+"\",\r\n       \"data\": {\r\n         \"dnrs\":[\r\n          {\r\n            \"id\":\""+dnrid+"\",\r\n            \"fromDate\": \""+date+"\",\r\n            \"toDate\": \""+date1+"\",\r\n            \"comment\":\"description For edit DNR mukesh test\"\r\n          }\r\n       ]\r\n     }\r\n   }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responseeditdnrs.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Editdnrscall() method

	public String extractingmessageeditdnrs()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String editdnrssuccessstring;
		editdnrssuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last editdnrs success"+editdnrssuccessstring);
		return editdnrssuccessstring;
	}// End of extractingmessageeditdnrs()
}// End of class
