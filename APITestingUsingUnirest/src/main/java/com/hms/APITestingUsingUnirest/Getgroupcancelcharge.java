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

public class Getgroupcancelcharge
{
static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String bookingType;
	String accesskey;
	String nightauditdate;
	
	public void Getgroupcancelchargecall(String s1, String s2)
	{
		keytype = s1;
		bookingType = s2;
		System.out.println("keytype in getgroupcancelcharge:"+s1);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in getgroupcancellationcharge:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getgroupcanellationcharge:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			System.out.println("hello getgroupcancelchagre:");
			serverurl = CommonConfig.serverurl;
			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall("login");
			//getbookingsobj.extractinggrouptempid();
			System.out.println("after getbookingscall in getgroupcancelcharge:");
			
			
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcall("login","RESERVE","G");
			//String editbookingtempid = editbookingobj.extractingtempid();
			//System.out.println("editbooking's group temp id :"+editbookingtempid);
			Getbookings getbookingsobj1 =new Getbookings();
			getbookingsobj1.Getbookingscall("login");
			getbookingsobj1.extractinggroupmainid();
			String editbookingtempid = Getbookings.getGroupTempID;
			System.out.println("after editbookingscall:::");
			System.out.println("maya"+editbookingtempid);
			
			
			HttpResponse<JsonNode> responsegetgroupcancellationcharge = Unirest.post(""+serverurl+"/ws/web/getgroupcancelcharge")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "bf2ab824-c00c-e001-6390-f5f1e6e6a0ed")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getgroupcancelcharge\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \t\"id\":\""+editbookingtempid+"\"\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsegetgroupcancellationcharge.getBody();
			System.out.println("get group cancellation charge:"+body);
			responseJSONString = body.toString();
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Getgroupcancelchargecall() method
	
	public String extractingmessagegetgroupcancellationcharge()
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
			commiteditbookingobj.Commiteditbookingcall("login","RESERVE","G");
		}
	}// End of extractingmessagegetgroupcancellationcharge() method
}
