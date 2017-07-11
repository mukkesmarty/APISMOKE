package com.hms.APITestingUsingUnirest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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


public class Search
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 String nightauditdate;
	 String nextnightauditdate;
	 String nexttonextnightauditdate;
	 public void Searchcall(String s1)
	 {
		 System.out.println("hello searchcall");
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
				System.out.println("wsauthkey in search:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in search:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				nextnightauditdate = CommonConfig.nightauditdate1;
				nexttonextnightauditdate = CommonConfig.nightauditdate2;
				
				
				//converting string to date
				
				/*DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = format.parse(nightauditdate);
				System.out.println("date output:"+date);*/
				
				
				//LocalDate date = LocalDate.parse(nightauditdate);
				//System.out.println("output:"+date);
				//System.out.println("hi");
				
				//String nightauditdatevar = nightauditdate + "T00:00:00Z";
				
				//Instant now = Instant.parse(nightauditdatevar);
				
				//Instant now = Instant.padate);
				//System.out.println("mukesh");
				//Instant after = now.plus(Duration.ofDays(1));
				//Date dateAfter = Date.from(after);
				//String s2 = dateAfter.toString();
				//System.out.println("mk");
				//System.out.println("hello"+s2);
				
				
				// need to format above date
				
				
				
				
				/*
				java.util.Date date = new Date("Sun Oct 25 18:41:35 IST 2015");
				SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
				String format = formatter.format(date);
				System.out.println(format);
				
				
				System.out.println("instant output"+ dateAfter);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
				System.out.println(formatter.format(s2));
				System.out.println("end");
				
				//LocalDate date1= LocalDate.parse(s2, formatter);
				//System.out.println("hahah"+date1);
				
				*/
				
			 HttpResponse<JsonNode> responsesearch = Unirest.post(""+serverurl+"/ws/web/search")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ce256b7f-52b2-0c1b-838f-8720520fd81e")
					  .body("{\r\n    \"hotelogix\": {\r\n        \"version\": \"1.0\",\r\n        \"datetime\": \"2012-01-16T10:10:15\",\r\n        \"request\": {\r\n         \"method\": \"search\",\r\n         \"key\": \""+accesskey+"\",\r\n         \"languagecode\": \"en\",\r\n         \"data\": {\r\n           \"stay\": {\r\n             \"checkindate\": \""+nextnightauditdate+"\",\r\n             \"checkoutdate\": \""+nexttonextnightauditdate+"\"\r\n           },\r\n           \"pax\": {\r\n             \"adult\": \"1\",\r\n             \"child\": \"0\",\r\n             \"infant\": \"0\"\r\n           },\r\n           \"searchfor\": {\"value\": \"TA\"},\r\n           \"ignorelists\": {\"cancellationpolicy\":[]},\r\n           \r\n           \"hotels\": [\r\n             {\r\n               \"id\": "+hotelid1+"\r\n             }\r\n           ],\r\n           \r\n           \"roomrequire\": 1,\r\n           \"limit\": {\r\n             \"value\": 20,\r\n             \"offset\": 0\r\n           }\r\n         }\r\n       }\r\n    }\r\n}")
					  .asJson();
			 JsonNode body = responsesearch.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 } 
		 
		 /*catch (ParseException e)
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	 }// End of Searchcall() method
	 
	 public String extractingmessagesearch()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String searchstring;
			searchstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last search success:"+searchstring);
			return searchstring;
	 }// End of extractingmessagesearch()
	 
	 public String extractingrateid()
	 {
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String rateid;
		 JSONArray hotelsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels");
		 System.out.println(hotelsArray);
		 
		 JSONArray roomtypesArray = hotelsArray.getJSONObject(0).getJSONArray("roomtypes");
		 System.out.println(roomtypesArray);
		  
		JSONArray ratesArray = roomtypesArray.getJSONObject(0).getJSONArray("rates");
		  System.out.println(ratesArray);
		  
		  JSONObject ratesobject = ratesArray.getJSONObject(0);
		  rateid = ratesobject.get("id").toString();
		  System.out.println(rateid);
		 
		 return rateid;
	 }
	 
	 
}// End of class

