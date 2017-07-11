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

public class Attachaddons
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Attachaddonscall(String s1)
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
				System.out.println("wsauthkey in attachaddons:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in attachaddons:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to attachaddons try block:");
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello accesskey in attachaddons:"+accesskey);
			 
			 Getaddons getaddonsobj = new Getaddons();
			 getaddonsobj.Getaddonscall(keytype);
			 String addonsid = getaddonsobj.extractingaddonsid();
			 String itemid = getaddonsobj.extractingitemidfromrequest();
			 
			 HttpResponse<JsonNode> responseattachaddons = Unirest.post(""+serverurl+"/ws/web/attachaddons")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "a0276e16-bcf7-cc05-6e5f-6d223db2499f")
					  .body("{\r\n   \"hotelogix\": {\r\n     \"version\": \"1.0\",\r\n     \"datetime\": \"2012-01-16T10:10:15\",\r\n     \"request\": {\r\n       \"method\": \"attachaddons\",\r\n       \"key\": \""+accesskey+"\",\r\n       \"languagecode\": \"en\",\r\n       \"data\": {\r\n         \"itemId\": {\r\n           \"value\": \""+itemid+"\"\r\n         },\r\n         \"addons\": [\r\n             {\r\n           \"id\": \""+addonsid+"\"\r\n         }]\r\n       }\r\n\r\n     }\r\n   }\r\n }")
					  .asJson();
			 
			 JsonNode body = responseattachaddons.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Attachaddonscall() method
	 
	 public String extractingmessageattachaddons()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String attachaddonsstring;
			attachaddonsstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last attachaddons:"+attachaddonsstring);
			return attachaddonsstring;
	 }// End of extractingmessagegetaddons() method
}// End of class
