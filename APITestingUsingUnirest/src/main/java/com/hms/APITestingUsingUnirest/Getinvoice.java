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


public class Getinvoice
{
	
static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	
	public void Getinvoicecall(String s)
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
			System.out.println("wsauthkey in getinvoice:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getinvoice:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			Getinvoices getinvoicesobj = new Getinvoices();
	//		getinvoicesobj.Getinvoicescall("login");
			String invoiceid = getinvoicesobj.extractinginvoiceid();
			System.out.println("invoice id in getinvoice"+invoiceid);
			
			HttpResponse<JsonNode> responsegetinvoice = Unirest.post(""+serverurl+"/ws/web/getinvoice")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "2ddab0a3-b56d-bb9d-ffe8-4322aa23d2f1")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getinvoice\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"invoiceId\": \""+invoiceid+"\"\r\n       }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responsegetinvoice.getBody();
			responseJSONString = body.toString();
			System.out.println("getinvoice response"+responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of Getinvoicescall() method
	
	public String extractingmessagegetinvoice()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getinvoicestring;
		getinvoicestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getinvoice success :"+getinvoicestring);
		return getinvoicestring;
	}// End of extractingmessagegetinvoice() method
}
