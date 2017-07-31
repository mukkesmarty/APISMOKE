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

public class Getroomtypestochange
{
	static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Getroomtypestochangecall(String s)
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
			System.out.println("wsauthkey in getroomtypestochange:"+keyw);

		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getroomtypestochange:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			HttpResponse<JsonNode> responsegetroomtypestochange = Unirest.post(""+serverurl+"/ws/web/getroomtypestochange")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "dd3ff36c-b131-c5cd-c07d-e4885e2de155")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getroomtypestochange\",\r\n      \"key\": \""+accesskey+"\"\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsegetroomtypestochange.getBody();
			responseJSONString = body.toString();
			System.out.println("getroomtypestochange response"+responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}//End of Getroomtypestochangecall() method
	
	public String extractingmessagegetroomtypestochange()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getroomtypestochangestring;
		getroomtypestochangestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getroomtypestochange success :"+getroomtypestochangestring);
		return getroomtypestochangestring;
	}// extractingmessagegetroomtypestochange() method
	
	public String extractingroomtypes()
	{
		System.out.println("welcome to extractingroomtypes");
		String localresponseJSONString = responseJSONString;
		String getroomtypesstring1="hi";
		JSONObject getroomtypesobject;
		
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		// this code will extract second room type of the hotel each time
		getroomtypesobject = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("availableRoomTypes").getJSONObject(1);
		String roomtypeid = getroomtypesobject.getString("id");
		
		
		System.out.println("extractingroomtypes output"+getroomtypesobject);
		System.out.println("roomtyp id :"+roomtypeid);
		return roomtypeid;	
	}
}//End of the class
