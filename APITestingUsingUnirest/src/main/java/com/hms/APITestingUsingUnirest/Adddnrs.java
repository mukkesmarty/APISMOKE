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

public class Adddnrs
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Adddnrscall(String s1)
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
				System.out.println("wsauthkey in adddnrs:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in adddnrs:"+keyl);
				accesskey = keyl;
			} 
		 
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 String date = CommonConfig.nightauditdate1;
			 String date1 = CommonConfig.nightauditdate2;
			 
			 Gethousestatus gethousestatusobj = new Gethousestatus();
			 gethousestatusobj.Gethousestatuscall(keytype);
			 String roomid = gethousestatusobj.extractingunassignroomsid();
			 
			 HttpResponse<JsonNode> responseadddnrs = Unirest.post(""+serverurl+"/ws/web/adddnrs")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "d1d9a5ab-ae5f-7fd7-d7ae-35d462d933f8")
					  .body("{\r\n \"hotelogix\": {\r\n  \"version\": \"1.0\",\r\n   \"datetime\": \"2012-01-16T10:10:15\",\r\n   \"request\": {\r\n     \"method\": \"adddnrs\",\r\n     \"key\": \""+accesskey+"\",\r\n     \"data\": {               \r\n        \"rooms\":[\r\n            {\r\n               \"id\":\""+roomid+"\",\r\n               \"fromDate\": \""+date+"\",\r\n               \"toDate\": \""+date1+"\",\r\n               \"comment\":\"descriptionForDNR 1\"\r\n            }\r\n        ]\r\n     }\r\n   }\r\n }\r\n}")
					  .asJson();
			
			 
			 JsonNode body = responseadddnrs.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Adddnrscall() method
	 
	 public String extractingmessageadddnrs()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String adddnrsstring;
			adddnrsstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last adddnrsstring::"+adddnrsstring);
			return adddnrsstring;
	 }// End of extractingmessageadddnrs() method
	 
}// End of class
