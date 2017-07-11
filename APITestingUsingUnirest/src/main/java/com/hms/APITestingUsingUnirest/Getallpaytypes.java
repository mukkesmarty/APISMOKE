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


public class Getallpaytypes
{
		 static String responseJSONString;
		 String serverurl;
		 String hotelid1;
		 String keytype;
		 String accesskey;
		 
		 public void Getallpaytypescall(String s1)
		 {
			 System.out.println("hello getallpaytypescall");
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
					System.out.println("login key in getallpaytypes:"+keyl);
					accesskey = keyl;
				}
			 
			 /*Wsauth objwsauth = new Wsauth();
			 objwsauth.Wsauthcall();
			 String wsauthaccesskey = objwsauth.extractingWsauthKey();
			 System.out.println("wsauthaccesskey::"+wsauthaccesskey);*/
			 
			 try{
				 serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
			 HttpResponse<JsonNode> responsegetallpaytypes = Unirest.post(""+serverurl+"/ws/web/getallpaytypes")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "3463a3e4-fdee-3c07-1af0-3a50681d80da")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getallpaytypes\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n          \"hotels\": [\r\n            {\r\n              \"id\": \""+hotelid1+"\"\r\n            }\r\n          ]\r\n        }\r\n    }\r\n  }\r\n}")
					  .asJson();
			 System.out.println("heloo::"+responsegetallpaytypes);
			 responseJSONString = responsegetallpaytypes.getBody().toString();
			 }
			 catch (UnirestException e)
			 {
				 e.printStackTrace();
			 }
		 }//End of Getallpaytypescall(String s1) method
			 
			 public String extractingmessageGetallpaytypes()
			 {
				 String localresponseJSONString = responseJSONString;
				 JSONObject jsonResult = new JSONObject(localresponseJSONString);
				 String getallpaytypessuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
				 return getallpaytypessuccessstring;
			 }// End of extractingmessageGetallpaytypes() method
}// End of class
		 

