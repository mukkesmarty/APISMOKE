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

public class Movebooking
{

	static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate;

	public void Movebookingcall(String s1)
	{
		keytype = s1;
		System.out.println("keytype in movebooking:"+s1);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in movebooking:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in movebooking:"+keyl);
			accesskey = keyl;
		}
		try
		{
			serverurl = CommonConfig.serverurl;
			//getting roomtype id
			Getroomtypestochange getroomtypestochangeobj = new Getroomtypestochange();
			getroomtypestochangeobj.Getroomtypestochangecall(keytype);
			String roomtypeid = getroomtypestochangeobj.extractingroomtypes();
			System.out.println("room type id movebooking:"+roomtypeid);
			
			//getting single booking id 
			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall(keytype);
			String bookingmainid = getbookingsobj.extractingmainid();
			
			
			HttpResponse<JsonNode> responsemovebooking = Unirest.post(""+serverurl+"/ws/web/movebooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "44f81d91-22f1-8a92-6519-55cdf5a32fbf")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"movebooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n            \"id\": \""+bookingmainid+"\",\r\n            \"roomTypeId\": \""+roomtypeid+"\",\r\n            \"upgradeRoom\":false\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responsemovebooking.getBody();
			System.out.println("movebooking:"+body);
			responseJSONString = body.toString();
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Movebookingcall(String s1) method
	
	public String extractingmessagemovebooking()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String movebookingstring;
		movebookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last movebooking success :"+movebookingstring);
		return movebookingstring;
	}// End of extractingmessagemovebooking() method
}
