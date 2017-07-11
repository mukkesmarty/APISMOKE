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

public class Gethousestatus
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String date1;
	String date2;
	
	public void Gethousestatuscall(String s)
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
			System.out.println("wsauthkey in gethousestatus:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			// Get the current date and time
						LocalDateTime currentTime = LocalDateTime.now();
						System.out.println("Current DateTime: " + currentTime);
						int year = Year.now().getValue()+1;
						System.out.println(year);
						System.out.println("accesskey in tryblock gethousestatus:"+accesskey);
						serverurl = CommonConfig.serverurl;
						date1 = CommonConfig.nightauditdate;
						date2 = CommonConfig.nightauditdate1;
						
						
						
			HttpResponse<JsonNode> responsegethousestatus = Unirest.post(""+serverurl+"/ws/web/gethousestatus")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "7448075d-4dd5-078a-b31e-61ae542e7c2d")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"gethousestatus\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n          \"fromDate\": \""+date2+"\",\r\n          \"toDate\": \""+date2+"\"\r\n       }    \r\n    }\r\n  }\r\n}")
					  .asJson();
			
			System.out.println("hello after post gethousestatus");
			JsonNode body = responsegethousestatus.getBody();
			System.out.println("gethousestatus response:"+body);
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Gethousestatuscall() method
	
	public String extractingmessagegethousestatus()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String gethousestatusstring;
		gethousestatusstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last gethousestatus success:"+gethousestatusstring);
		return gethousestatusstring;
	}// End of extractingmessagegethousestatus() method
	
	public String extractingroomtypeids()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray roomtypesArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("days").getJSONObject(0).getJSONArray("roomTypes");
		JSONObject roomtypeid = roomtypesArray.getJSONObject(0);
		
		System.out.println(roomtypeid.get("id"));
		String rmtpid = roomtypeid.getString("id");
		System.out.println("roomtype id is :"+rmtpid);
		String getroomtypeidstring = rmtpid;
		return getroomtypeidstring;
		
	}//End of extractingroomtypeids() method
	public String extractingunassignroomsid()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray roomtypesArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("days").getJSONObject(0).getJSONArray("roomTypes");
		JSONArray unassignrommsArray = roomtypesArray.getJSONObject(0).getJSONArray("unassigRooms");
		String roomid = unassignrommsArray.getJSONObject(0).get("id").toString();
		System.out.println("room id in gethousestatus:"+roomid);
		return roomid;
	}// End of extractingunassignroomsid() method 
}// End of class
