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

public class Getbookings
{
	static String responseJSONString;
	static String getGroupCode;
	static String getGroupTempID;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate1;
	String restype;
	String resstatus;
	
	
	public void Getbookingscall(String s)
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
			System.out.println("login key in getbookings:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate1 = CommonConfig.nightauditdate1;
			
			
			HttpResponse<JsonNode> responsegetbookings = Unirest.post(""+serverurl+"/ws/web/getbookings")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "442ccdd8-438b-68ed-d7c1-0aaed145ef9b")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getbookings\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"fromDate\": \""+nightauditdate1+"\",\r\n         \"toDate\": \""+nightauditdate1+"\",\r\n         \"searchBy\": \"STAYDATE\",\r\n         \"reservationStatus\":[\"RESERVE\",\"CHECKIN\",\"CHECKOUT\",\"BLOCKED\",\"CANCEL\"]\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responsegetbookings.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}//End of Getbookingscall() method for single parameter
	
	
	
	public void Getbookingscall(String s1,String s2)
	{
		keytype = s1;
		
		System.out.println("keytype:"+s1);
		
		resstatus = s2;
		System.out.println("resstatus:"+s2);
		
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
			System.out.println("login key in getbookings:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate1 = CommonConfig.nightauditdate1;
			
			HttpResponse<JsonNode> responsegetbookings = Unirest.post(""+serverurl+"/ws/web/getbookings")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "442ccdd8-438b-68ed-d7c1-0aaed145ef9b")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getbookings\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"fromDate\": \""+nightauditdate1+"\",\r\n         \"toDate\": \""+nightauditdate1+"\",\r\n         \"searchBy\": \"STAYDATE\",\r\n         \"reservationStatus\":[\""+resstatus+"\"]\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responsegetbookings.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of Getbookingscall() method for two parameter
	
	
	
	public String extractingmessagegetbookings()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingsstring;
		getbookingsstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbookings success:"+getbookingsstring);
		return getbookingsstring;
	}// End of extractingmessagegetbookings() method
	
	public String extractingcode()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingscode;
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		JSONObject bookingscode = getbookingsArray.getJSONObject(0);
		getbookingscode = bookingscode.getString("code");
		System.out.println(":getbookings code:"+getbookingscode);
		return getbookingscode;
	}//End of extractingcode() method
	
	public String extractinggroupcode()
	{
		System.out.println("welcome to extractinggroupcode");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingsgroupcode;
		
		JSONArray getbookingsarray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		int l = getbookingsarray.length();
		System.out.println("length"+l);
		
		String getgroupcode = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings").getJSONObject(l-2).getJSONObject("group").getString("code");
		System.out.println(getgroupcode);
		
		System.out.println(getbookingsarray);
		
		//String groupcode = "hi";
		
		System.out.println("group code:"+getgroupcode);
		getGroupCode = getgroupcode;
		return getgroupcode;
	}// End of extractinggroupcode() method
	
	
	public String extractinggroupmainid()
	{
		System.out.println("welcome to extractinggrouptempid");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingsgroupcode;
		
		JSONArray getbookingsarray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		int l = getbookingsarray.length();
		System.out.println("length"+l);
		
		String getgrouptempid = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings").getJSONObject(l-2).getJSONObject("group").getString("id");
		System.out.println(getgrouptempid);
		
		System.out.println(getbookingsarray);
		
		//String groupcode = "hi";
		
		System.out.println("group temp id:"+getgrouptempid);
		getGroupTempID = getgrouptempid;
		return getgrouptempid;
	}// End of extractinggrouptempid() method
	
	
	public String extractingmainid()
	{
		System.out.println("welcome to extractingmainid for single reservation");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingsmainid;
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		JSONObject bookingsmainid = getbookingsArray.getJSONObject(0);
		getbookingsmainid = bookingsmainid.getString("mainId");
		System.out.println(":getbookings mainid:"+getbookingsmainid);
		return getbookingsmainid;
	}//End of extractingmainid() method
}
