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

public class Loadcart
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Loadcartcall(String s1)
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
			 System.out.println("welcome to loadcart try block:");
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello accesskey:"+accesskey);
			 Addtocart addtocartobj = new Addtocart();
			 addtocartobj.Addtocartcall(keytype);
			 
			 HttpResponse<JsonNode> responseloadcart = Unirest.post(""+serverurl+"/ws/web/loadcart")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "cf283663-49a4-0fb9-f83c-95bed8c0718f")
					  .body("{\r\n  \"hotelogix\": {\r\n            \"version\": \"1.0\",\r\n            \"datetime\": \"2012-01-16T10:10:15\",\r\n            \"request\": {\r\n                \"method\": \"loadcart\",\r\n                \"key\": \""+accesskey+"\",\r\n                \"languagecode\": \"en\"\r\n            }\r\n  }\r\n    }")
					  .asJson();
			 
			 JsonNode body = responseloadcart.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	 }// End of Loadcartcall() method
	 
	 public String extractingmessageloadcart()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String loadcartstring;
			loadcartstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last search loadcart:"+loadcartstring);
			return loadcartstring;
	 }//End of extractingmessageloadcart() method
	 
	 public String extracingbookingid()
	 {
		 String bookingid="hi";
		 String localresponseJSONString = responseJSONString;
		 
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 JSONArray hotelsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels");
		 System.out.println(hotelsArray);
		 
		 JSONArray bookingsArray = hotelsArray.getJSONObject(0).getJSONArray("bookings");
		 System.out.println(bookingsArray);
		 
		 JSONObject bookingsobject  = bookingsArray.getJSONObject(0);
		 bookingid = bookingsobject.get("id").toString();
		 System.out.println("gotta::"+bookingid);
		 
		 return bookingid;
	 }// End of extractingbookingid()
	 
	 public String extractinghotelid()
	 {
		 String hotelid = "testhotelid";
		 
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 JSONArray hotelsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels");
		 hotelid = hotelsArray.getJSONObject(0).get("id").toString();
		 System.out.println("hotelid in extractinghotelid: "+hotelid);
		 
		 return hotelid;
	 }// End of extractinghotelid() method
	 
	 public String extractinggueststayid()
	 {
		 String gueststayid = "testgueststayid";
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 JSONArray hotelsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels");
		 
		 JSONArray bookingsArray = hotelsArray.getJSONObject(0).getJSONArray("bookings");
		 JSONArray gueststaysArray = bookingsArray.getJSONObject(0).getJSONArray("gueststays");
		 
		 gueststayid = gueststaysArray.getJSONObject(0).get("id").toString();
		 System.out.println("gueststayid in extractinggueststayid:::"+gueststayid);
		 
		 
		 return gueststayid;
	 }// End of extractinggueststayid() method
}// End of class
