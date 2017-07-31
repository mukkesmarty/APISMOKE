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

public class Opencloseoverbooking
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Opencloseoverbookingcall(String s)
	{
		keytype = s;
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in opencloseoverbooking:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;*/

				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in opencloseoverbooking:"+keyl);
				accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			String roomtypecode1hotelid1 = CommonConfig.roomtypecode1hotelid1;
			String roomtypecode2hotelid1 = CommonConfig.roomtypecode2hotelid1;
			
			// Get the current date and time
			LocalDateTime currentTime = LocalDateTime.now();
			System.out.println("Current DateTime: " + currentTime);
			int year = Year.now().getValue()+1;
			System.out.println(year);
			
			HttpResponse<JsonNode> responseopencloseoverbooking = Unirest.post(""+serverurl+"/ws/web/opencloseoverbooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "0bab028c-1893-56da-9d96-594b5d2eebc5")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2016-05-02T12:34:18\",\r\n    \"request\": {\r\n      \"method\": \"opencloseoverbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\":{\r\n      \"hotels\":[\r\n        {\r\n            \"id\":"+hotelid1+",\r\n            \"fromDate\":\""+year+"-11-17\",\r\n            \"toDate\": \""+year+"-11-20\",\r\n            \"type\" : \"close\",\r\n            \"roomTypes\":[\r\n                \r\n                {\r\n                \t\"roomTypeCode\":\""+roomtypecode1hotelid1+"\"\r\n                },\r\n                {\r\n                \t\"roomTypeCode\": \""+roomtypecode2hotelid1+"\"\r\n                }\r\n                \r\n            ]\r\n        }\r\n        \r\n       ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responseopencloseoverbooking.getBody();
			System.out.println("opencloseoverbooking response:"+body);
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Opencloseoverbookingcal() method
	
	public String extractingmessageopencloseoverbooking()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String opencloseoverbooking;
		opencloseoverbooking = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last opencloseoverbooking success"+opencloseoverbooking);
		return opencloseoverbooking;
	}// End of extractingmessageopencloseoverbooking() method
}
