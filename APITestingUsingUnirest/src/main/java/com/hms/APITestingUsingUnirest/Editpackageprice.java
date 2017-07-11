package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

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


public class Editpackageprice
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String hotelid2;
	String keytype; 
	String accesskey;
	
	public void Editpackagepricecall(String s)
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
			hotelid2 = CommonConfig.hotelid2;
			String roomtypecode1hotelid1 = CommonConfig.roomtypecode1hotelid1;
			String roomtypecode2hotelid1 = CommonConfig.roomtypecode2hotelid1;
			String roomtypecode1hotelid2 = CommonConfig.roomtypecode1hotelid2;
			String roomtypecode2hotelid2 = CommonConfig.roomtypecode2hotelid2;
			String dailyratecode1hotelid1 = CommonConfig.dailyratecode1hotelid1;
			String dailyratecode1hotelid2 = CommonConfig.dailyratecode2hotelid2;
			
			// Get the current date and time
						LocalDateTime currentTime = LocalDateTime.now();
						System.out.println("Current DateTime: " + currentTime);
						int year = Year.now().getValue()+1;
						System.out.println(year);
						
						int minimum = 100;
						int maximum = 9999;
						int randomNum;
						randomNum = minimum + (int)(Math.random() * maximum);
			
			HttpResponse<JsonNode> responseeditpackageprice = Unirest.post(""+serverurl+"/ws/web/editpackageprice")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "02dbd63f-771e-11c4-6ce8-d8e52761167e")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2016-05-02T12:34:18\",\r\n    \"request\": {\r\n      \"method\": \"editpackageprice\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\":{\r\n      \"hotels\":[\r\n        {\r\n            \"id\":"+hotelid1+",\r\n            \"rateCode\": \""+dailyratecode1hotelid1+"\",\r\n            \"rates\":[\r\n                {\r\n                    \"roomTypeCode\":\""+roomtypecode1hotelid1+"\",\r\n                    \"fromDate\":\""+year+"-12-25\",\r\n                    \"toDate\":\""+year+"-12-30\",\r\n                    \"occupancy\":[\r\n                        {\r\n                            \"type\":\"Adult\",\r\n                            \"number\":-2,\r\n                            \"amount\":"+randomNum+"\r\n                        },\r\n                        {\r\n                            \"type\":\"Child\",\r\n                            \"number\":1,\r\n                            \"amount\":36\r\n                        }\r\n                    ],\r\n                    \"extraBedPrice\":695\r\n\r\n                }\r\n            ]\r\n        },\r\n        \r\n        {\r\n            \"id\":"+hotelid2+",\r\n            \"rateCode\": \""+dailyratecode1hotelid2+"\",\r\n            \"rates\":[\r\n                {\r\n                    \"roomTypeCode\":\""+roomtypecode1hotelid2+"\",\r\n                    \"fromDate\":\""+year+"-12-25\",\r\n                    \"toDate\":\""+year+"-12-30\",\r\n                    \"occupancy\":[\r\n                        {\r\n                            \"type\":\"Adult\",\r\n                            \"number\":2,\r\n                            \"amount\":"+randomNum+"\r\n                        },\r\n                        {\r\n                            \"type\":\"Child\",\r\n                            \"number\":1,\r\n                            \"amount\":36\r\n                        }\r\n                    ],\r\n                    \"extraBedPrice\":695\r\n\r\n                }\r\n            ]\r\n        }\r\n        \r\n       ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responseeditpackageprice.getBody();
			System.out.println("opencloseoverbooking response:"+body);
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Editpackagepricecall() method
	
	public String extractingmessageeditpricepackage()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String editpackageprice;
		editpackageprice = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last editpackageprice success"+editpackageprice);
		return editpackageprice;
	}
}
