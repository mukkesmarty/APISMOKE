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

public class Deletefromcart
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Deletefromcartcall(String s1)
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
				System.out.println("wsauthkey in deletefromcart:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in deletefromcart:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to deletefromcart :");
			 Loadcart loadcartobj = new Loadcart();
			 loadcartobj.Loadcartcall(keytype);
			 String bookingid = loadcartobj.extracingbookingid();
			 
			 System.out.println("welcome to deletefromcart try block:");
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello accesskey in deletefromcart:"+accesskey);
			 
			 HttpResponse<JsonNode> responsedeletefromcart = Unirest.post(""+serverurl+"/ws/web/deletefromcart")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "197669f0-2996-fe9a-6cba-e9c29716b373")
					  .body("{\r\n   \"hotelogix\": {\r\n     \"version\": \"1.0\",\r\n     \"datetime\": \"2012-01-16T10:10:15\",\r\n     \"request\": {\r\n       \"method\": \"deletefromcart\",\r\n       \"key\": \""+accesskey+"\",\r\n       \"languagecode\": \"en\",\r\n       \"data\": {\r\n         \"itemId\": {\r\n           \"value\": \""+bookingid+"\"\r\n         }\r\n       }\r\n\r\n     }\r\n   }\r\n }")
					  .asJson();
			 JsonNode body = responsedeletefromcart.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
			 
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	 }//End of Deletefromcartcall() method 
	
	public String extractingmessagedeletefromcart()
	{

		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String deletefromcartstring;
			deletefromcartstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last search deletefromcart:"+deletefromcartstring);
			return deletefromcartstring;
	}// End of extractingmessagedeletefromcart() method 
}// End of class
