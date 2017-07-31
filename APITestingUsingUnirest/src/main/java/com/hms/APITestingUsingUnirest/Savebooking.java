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

public class Savebooking
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Savebookingcall(String s1)
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
				System.out.println("wsauthkey in savebooking:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in savebooking:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to savebooking try block:");
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello accesskey in savebooking:"+accesskey);
			 
			 Loadcart loadcartobj = new Loadcart();
			 loadcartobj.Loadcartcall(keytype);
			 String hotelid = loadcartobj.extractinghotelid();
			 String bookingid = loadcartobj.extracingbookingid();
			 String gueststayid = loadcartobj.extractinggueststayid();
			 
			 HttpResponse<JsonNode> responsesavebooking = Unirest.post(""+serverurl+"/ws/web/savebooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "4bb5413d-2fd5-bbde-7d66-11c6df5ee394")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"savebooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"languagecode\": \"en\",\r\n      \"data\": {\r\n        \"guest\": {\r\n          \"fname\": {\r\n            \"value\": \"Mukesh\"\r\n          },\r\n          \"lname\": {\r\n            \"value\": \"Jha\"\r\n          },\r\n          \"email\": {\r\n            \"value\": \"mukesh.kumar@hotelogix.com\"\r\n          },\r\n          \"phone\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"mobile\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"country\": {\r\n            \"code\": \"IN\",\r\n            \"value\": \"India\"\r\n          },\r\n          \"state\": {\r\n            \"code\": \"UP\",\r\n            \"value\": \"noida\"\r\n          },\r\n          \"address\": {\r\n            \"value\": \"test address\"\r\n          },\r\n          \"city\": {\r\n            \"value\": \"noida\"\r\n          },\r\n          \"zip\": {\r\n            \"value\": \"201301\"\r\n          }\r\n        },\r\n        \"hotels\": [\r\n          {\r\n            \"id\": \""+hotelid+"\",\r\n            \"preference\": {\r\n              \"value\": \"nopreference\"\r\n            },\r\n            \"bookings\": {\r\n              \"booking\": {\r\n                \"id\": \""+bookingid+"\",\r\n                \"gueststays\": {\r\n                  \"gueststay\": {\r\n                    \"id\": \""+gueststayid+"\",\r\n                    \"guest\": {\r\n                      \"fname\": {\r\n                        \"value\": \"akriti\"\r\n                      },\r\n                      \"lname\": {\r\n                        \"value\": \"mishra\"\r\n                      },\r\n                      \"email\": {\r\n                        \"value\": \"akriti@hotelogix.com\"\r\n                      },\r\n                      \"phone\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"mobile\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"country\": {\r\n                        \"code\": \"IN\",\r\n                        \"value\": \"India\"\r\n                      },\r\n                      \"state\": {\r\n                        \"code\": \"br\",\r\n                        \"value\": \"Bihar\"\r\n                      },\r\n                      \"address\": {\r\n                        \"value\": \"mayur vihar\"\r\n                      },\r\n                      \"city\": {\r\n                        \"value\": \"madhubani\"\r\n                      },\r\n                      \"zip\": {\r\n                        \"value\": \"110091\"\r\n                      }\r\n                    }\r\n                  }\r\n                }\r\n              }\r\n            }\r\n          }\r\n        ],\r\n        \"holdTill\": {\r\n          \"releaseAfter\": {\r\n            \"value\": \"20\",\r\n            \"type\": \"hour\"\r\n          }\r\n        }\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			 JsonNode body = responsesavebooking.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Savebookingcall() method
	 
	 public String extractingmessagesavebooking()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String savebookingstring;
			savebookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last savebooking::"+savebookingstring);
			return savebookingstring;
	 }// End of extractingmessagesavebooking() method
	 
	 public String extractingorderid()
	 {
		 System.out.println("welcome to extractingorderid:");
		 String orderid = "testorderid"; 
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 JSONObject orderobj = jsonResult.getJSONObject("hotelogix").getJSONObject("response");
		 orderid = orderobj.getJSONObject("order").getString("id").toString();
		 
		 System.out.println("order id in savebooking"+orderid);
		 return orderid;
	 }// End of extractingorderid() method
	 
	 public String extractingdeposittotal()
	 {
		 System.out.println("welcome to extractingdeposittotal:");
		 String deposittotal = "testdeposittotal";
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 deposittotal =jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("order").getJSONObject("deposittotal").get("amount").toString();
		 System.out.println("deposittotal in savebooking:");
		 return deposittotal;
	 }// End of extractingdeposittotal() method
}// End of class
