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


public class Getpaytype
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Getpaytypecall(String s1)
	 {
		 keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if getpaytype:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in getpaytype:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getpaytype:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello accesskey in getpaytype:"+accesskey);
			 
			 HttpResponse<JsonNode> responsegetpaytype = Unirest.post(""+serverurl+"/ws/web/getpaytype")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "a3c170e0-43f5-2764-8e80-e9dde197df7b")
					  .body("{\r\n   \"hotelogix\": {\r\n     \"version\": \"1.0\",\r\n     \"datetime\": \"2012-01-16T10:10:15\",\r\n     \"request\": {\r\n       \"method\": \"getpaytype\",\r\n       \"key\": \""+accesskey+"\"\r\n     }\r\n   }\r\n}")
					  .asJson();
			 
			 JsonNode body = responsegetpaytype.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	 }// End of Getpaytypecall() method
	 
	 public String extractingmessagegetpaytype()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String gethoteloptionsstring;
			gethoteloptionsstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last getpaytype:"+gethoteloptionsstring);
			return gethoteloptionsstring;
	 }// End of extractingmessagegetpaytype() method
	 
}// End of class
