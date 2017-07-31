package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;

public class Deleteproformainvoice
{
	static String responseJSONString;
	static String finalloginaccesskey;
	String serverurl;
	String hotelid1;
	String key;
	String emailid;
	String password;
	String keytype;
	String accesskey;
	String bookingtype;
	String mainbookingid;
	
	public void Deleteproformainvoicecall(String s1,String s2)
	{
		keytype = s1;
		bookingtype = s2;
		System.out.println("keytype:"+s1);
		System.out.println("keytype:"+s2);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauth key in getallpaytypes"+ keyw);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in deleteproformainvoice:"+keyw);
			
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in deleteproformainvoice:"+accesskey);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			if(bookingtype == "S")
			{
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall(keytype);
				mainbookingid = getbookingsobj.extractingmainid();
			}
			else
			{
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall(keytype);
				mainbookingid = getbookingsobj.extractinggroupmainid();
			}
			
			HttpResponse<JsonNode> responsedeleteproformainvoice = Unirest.post(""+serverurl+"/ws/web/deleteproformainvoice")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "c909d68b-091d-c284-9f0f-a26068c5ddd8")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"deleteproformainvoice\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"id\": \""+mainbookingid+"\",\r\n         \"type\":\""+bookingtype+"\"\r\n       }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsedeleteproformainvoice.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Deleteproformainvoicecall() method
	public String extractingmessagedeleteproformainvoice()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String deleteproformainvoicestring;
		deleteproformainvoicestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last deleteproformainvoice success:"+deleteproformainvoicestring);
		return deleteproformainvoicestring;
	}// End of extractingmessagedeleteproformainvoice() method
}// End of class
