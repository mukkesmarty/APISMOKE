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


public class Getearlycheckincharge
{
static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String searchtext;
	
	public void getearlycheckinchargecall(String s1,String s2)
	{
		keytype = s1;
		searchtext = s2;
		System.out.println("keytype:"+s1);
		System.out.println("searctext:"+s2);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if in getinvoices:"+ accesskey);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in getearlycheckincharge:"+keyw);
	
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getearlycheckincharge:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			//
			System.out.println("hello getearlycheckincharge:: try block::");
			
			serverurl = CommonConfig.serverurl;
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcall(keytype, searchtext);
			String tempid = editbookingobj.extractingtempid();
			
			System.out.println("just before call:::");
			
			HttpResponse<JsonNode> responsegetearlycheckincharge = Unirest.post(""+serverurl+"/ws/web/getearlycheckincharge")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "a96d591c-a37a-aa90-0b43-a2a468329537")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getearlycheckincharge\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \"bookings\": [\r\n          {\r\n            \"id\": \""+tempid+"\"\r\n          }\r\n        ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsegetearlycheckincharge.getBody();
			responseJSONString = body.toString();
			System.out.println("getearlycheckout response:"+responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of getearlycheckinchargecall() method
	public String extractingmessagegetearlycheckincharge()
	{
		System.out.println("welcome to extractingmessagegetearlycheckincharge");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String editbookingstring;
		editbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getearlycheckincharge success:"+editbookingstring);
		return editbookingstring;
	}//End of extractingmessageeditbooking() method
}// End of class
