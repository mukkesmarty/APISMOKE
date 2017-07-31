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

public class Getaddons
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Getaddonscall(String s1)
	 {
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
				System.out.println("login key in addtocart:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to getaddons try block:");
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello accesskey in getaddons:"+accesskey);
			 
			 Loadcart loadcartobj = new Loadcart();
			 loadcartobj.Loadcartcall(keytype);
			 String bookingid = loadcartobj.extracingbookingid();
			 System.out.println("bookingid in getaddons try block:"+bookingid);
			 
			 HttpResponse<JsonNode> responsegetaddons = Unirest.post(""+serverurl+"/ws/web/getaddons")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "d4a39a1d-b030-c646-a199-87fdc8989abe")
					  .body("{\r\n   \"hotelogix\": {\r\n     \"version\": \"1.0\",\r\n     \"datetime\": \"2012-01-16T10:10:15\",\r\n     \"request\": {\r\n       \"method\": \"getaddons\",\r\n       \"key\": \""+accesskey+"\",\r\n       \"languagecode\": \"en\",\r\n       \"data\": {\r\n         \"itemId\": {\r\n           \"value\": \""+bookingid+"\"\r\n         }\r\n       }\r\n     }\r\n   }\r\n }")
					  .asJson();
			 
			 JsonNode body = responsegetaddons.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Getaddonscall() method
	 
	 public String exrtractingmessagegetaddons()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getaddonsstring;
			getaddonsstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last getaddons:"+getaddonsstring);
			return getaddonsstring;
	 }// End of extractingmessagegetaddons() method
	 
	 public String extractingitemidfromrequest()
	 {
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String itemidstring="hi";
		 JSONObject itemidobject = jsonResult.getJSONObject("hotelogix").getJSONObject("request").getJSONObject("data");
		 itemidstring = itemidobject.getJSONObject("itemId").getString("value");
		 System.out.println("hola:"+itemidstring);
		 return itemidstring;
	 }// End of extractingitemidfromrequest() method
	 
	 public String extractingaddonsid()
	 {
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String addonsid="testaddon";
		 JSONObject addonsobject = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("addons");
		 System.out.println("hello addons"+addonsobject);
		 addonsid = addonsobject.getJSONObject("0").getString("id");
		 System.out.println("addonsid in extractingaddonsid method"+addonsid);
		 return addonsid;
	 }// End of extractingaddonsid() method
}// End of class
