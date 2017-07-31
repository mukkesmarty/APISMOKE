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

public class Getinvoices
{
	
static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String searchtext;
	
	public void Getinvoicescall(String s1,String s2)
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
			System.out.println("wsauthkey in getinvoices:"+keyw);
	
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getinvoices:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			/*Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall("login");
			String mainid = getbookingsobj.extractingmainid();
			System.out.println("getbookings mainid in getinvoices"+mainid);*/
			
			Getbookingsearch getbookingsearchobj = new Getbookingsearch();
			getbookingsearchobj.Getbookingsearchcall(keytype, searchtext);
			String mainid = getbookingsearchobj.extractingmainid();
			
			
			
			HttpResponse<JsonNode> responsegetinvoices = Unirest.post(""+serverurl+"/ws/web/getinvoices")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "c6c8cdea-3cc5-7953-79c7-1d9850356bd2")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getinvoices\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"id\": \""+mainid+"\",\r\n         \"type\":\"S\"\r\n       }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responsegetinvoices.getBody();
			responseJSONString = body.toString();
			System.out.println("getinvoices response"+responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}//End of Getinvoicescall() method
	
	public String extractingmessagegetinvoices()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getinvoicesstring;
		getinvoicesstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getinvoices success :"+getinvoicesstring);
		return getinvoicesstring;
	}// End of extractingmessagegetinvoices() method
	
	public String extractinginvoiceid()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getinvoicestring;
		JSONArray getinvoicesarray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("invoices");
		JSONObject invoiceid = getinvoicesarray.getJSONObject(0);
		getinvoicestring = invoiceid.getString("invoiceId");
		System.out.println("get invoices invoice id:"+getinvoicestring);
		return getinvoicestring;
	}
}
