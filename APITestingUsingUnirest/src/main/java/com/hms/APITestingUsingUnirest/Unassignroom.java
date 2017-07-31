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

import java.lang.*;

public class Unassignroom
{

static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate;
	String restype = "RESERVE";
	String bookingtype = "G"; 
	
	public void unassignroomcall(String s1) throws InterruptedException
	{
		keytype = s1;
		System.out.println("keytype in unassignroom:"+s1);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in unassignroom:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in unassignroom:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcall(keytype,bookingtype,restype);
			System.out.println("after editbooking call");
			editbookingobj.extractingtempid();
			
			//Thread t;
			Thread.sleep(1000);
			System.out.println(":after thread sleep:");
			
			
			
			String editbookingtempid = editbookingobj.extractingtempid();
			System.out.println("after extractingtempid"+editbookingtempid);
			System.out.println("editbooking tempid in unassignroom:"+editbookingtempid);
			
			HttpResponse<JsonNode> responseunassignroom = Unirest.post(""+serverurl+"/ws/web/unassignroom")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "7eedb78f-6246-74dd-6d1d-51a43661f855")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"unassignroom\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \"bookings\": [\r\n          {\t\r\n            \"id\": \""+editbookingtempid+"\"\r\n          }\r\n        ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responseunassignroom.getBody();
			System.out.println("unassignroom:"+body);
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of unassignroom(String s1) method
	
	public String extractingmessageunassignroom()
	{
		try
		{
			String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getgroupcancellationchargestring;
			getgroupcancellationchargestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last getgroupcancellationcharge success :"+getgroupcancellationchargestring);
			return getgroupcancellationchargestring;
		}
		finally
		{
			System.out.println("getgroupcancellationcharge finally block ::");
			Commiteditbooking commiteditbookingobj = new Commiteditbooking();
			commiteditbookingobj.Commiteditbookingcall("keytype","G",restype);
		}
	}//End of extractingmessageunassignroom() method
}
