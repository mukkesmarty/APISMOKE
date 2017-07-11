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

public class Editbooking
{
static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype;
	String bookingtype;
	String accesskey;
	String restype;
	String searchkey;
	
	
	public void Editbookingcall(String s1)
	{
		System.out.println("welcome to editbookingcall with one parameter");
		keytype = s1;
		System.out.println("keytype:"+s1);
		
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
			System.out.println("login key in editbooking:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall("login");
			String mainid = getbookingsobj.extractingmainid();
			System.out.println("getbookings mainid in editbooking:"+mainid);
			
			HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \"S\",\r\n         \"id\": \""+mainid+"\"\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responseeditbooking.getBody();
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Editbookingcall(s1) method
	
	
	public void Editbookingcall(String s1, String s2, String s3)
	{
		System.out.println("welcome to editbookingcall with three parameters");
		keytype = s1;
		bookingtype = s2;
		restype = s3;
		
		
		System.out.println("keytype::"+s1);
		System.out.println("bookingtype::"+s2);
		System.out.println("restype::"+s3);
		
		if(keytype == "wsauth")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in editbooking:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall(keytype,restype);
			//String mainid = getbookingsobj.extractinggroupmainid();
			//getbookingsobj.extractingmainid();
			getbookingsobj.extractinggroupmainid();
			String mainid = Getbookings.getGroupTempID;
			System.out.println("getbookings group mainid in editbooking::"+mainid);
			
			HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \""+bookingtype+"\",\r\n         \"id\": \""+mainid+"\"\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responseeditbooking.getBody();
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Editbookingcall(s1,s2,s3) method
	
	
	
	public void Editbookingcall(String s1, String s2)
	{
		System.out.println("welcome to editbookingcall with two parameters");
		keytype = s1;
		searchkey = s2;
		
		
		
		System.out.println("keytype::"+s1);
		System.out.println("searchkey::"+s2);
		
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in editbooking:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in editbooking:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			//Getbookings getbookingsobj = new Getbookings();
			//getbookingsobj.Getbookingscall(keytype,restype);
			
			//String mainid = getbookingsobj.extractinggroupmainid();
			//getbookingsobj.extractingmainid();
			
			/*getbookingsobj.extractinggroupmainid();
			String mainid = Getbookings.getGroupTempID;
			System.out.println("getbookings group mainid in editbooking::"+mainid);*/
			
			Getbookingsearch getbookingsearchobj = new Getbookingsearch();
			getbookingsearchobj.Getbookingsearchcall(keytype, searchkey);
			String mainid = getbookingsearchobj.extractingmainid();
			
			
			HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \"S\",\r\n         \"id\": \""+mainid+"\"\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responseeditbooking.getBody();
			responseJSONString = body.toString();	
			System.out.println("editbooking:"+responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Editbookingcall(s1,s2) method
	
	
	public String extractingmessageeditbooking()
	{
		System.out.println("welcome to extractingmessageeditbooking");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String editbookingstring;
		editbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbooking success:"+editbookingstring);
		return editbookingstring;
	}//End of extractingmessageeditbooking() method
	
	public String extractingtempid()
	{
		System.out.println("welcome to extractingtempid");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingstempid;
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		JSONObject bookingstempid = getbookingsArray.getJSONObject(0);
		getbookingstempid = bookingstempid.getString("id");
		System.out.println(":getbookings mainid:"+getbookingstempid);
		return getbookingstempid;
	}//End of extractingtempid() method
}
