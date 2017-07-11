package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;


public class Createagent
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Createagentcall(String s)
	{
		keytype = s;
		
		if(keytype == "wsauth")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
		}
		
		else if(keytype == "login")
		{
			Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;
		}
		try
		{
			System.out.println("hello try createagent");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			String uniqueID = UUID.randomUUID().toString();
			//String email = uniqueID+"hotelogix.com";
			String str1 = uniqueID.substring(0,9);
			System.out.println("substring"+str1);
			// Get the current date and time
		      LocalDateTime currentTime = LocalDateTime.now();
		      System.out.println("Current DateTime: " + currentTime);
		      int year = Year.now().getValue()+1;
		      System.out.println(year);
		      
			
			HttpResponse<JsonNode> responsecreateagent = Unirest.post(""+serverurl+"/ws/web/createagent")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("\r\n\r\n{\r\n   \"hotelogix\": {\r\n       \"version\": \"1.0\",\r\n       \"datetime\": \"2012-01-16T10:11:15\",\r\n       \"request\": {\r\n           \"method\": \"createagent\",\r\n           \"key\": \""+accesskey+"\",\r\n           \"data\": {\r\n               \"groupCode\": \""+str1+"\",\r\n               \"iataCode\": \"A21\",\r\n               \"businessName\": \""+uniqueID+"\",\r\n               \r\n               \"tax\": \"120\",\r\n               \"deduction\": \"120\",\r\n               \"deductionAfterTax\": true,\r\n               \"addresses\": {\r\n                       \"office\": {\r\n                               \"address\": \"abcd\",\r\n                               \"country\": \"India\",\r\n                               \"state\": \"delhi\",\r\n                               \"city\": \"delhi\",\r\n                               \"zip\": \"123\"\r\n                       },\r\n                       \"billing\": {\r\n                               \"companyName\": \"billingCompany21\",\r\n                               \"address\": \"billingAddress21\",\r\n                               \"country\": \"Pakistan\",\r\n                               \"state\": \"Punjab\",\r\n                               \"city\": \"delhi\",\r\n                               \"zip\": \"123456\"\r\n                       }\r\n               },\r\n               \"contacts\": {\r\n                       \"personal\": {\r\n                               \"salutation\": \"Mr\",\r\n                               \"fName\": \"Mukul\",\r\n                               \"lName\": \"Mishra\",\r\n                               \"designation\": \"Manager\",\r\n                               \"phoneNo\": \"Manager\",\r\n                               \"phoneExtension\": \"101\",\r\n                               \"faxNo\": \"101\",\r\n                               \"email\": \""+uniqueID+"@hotelogix.com"+"\",\r\n                               \"mobileNo\": \"8802640811\",\r\n                               \"gender\": \"M\",\r\n                               \"dob\": \"2016-03-01\",\r\n                               \"website\": \"www.hotelogix.com\"\r\n                       },\r\n                       \"billing\": {\r\n                               \"salutation\": \"Mr\",\r\n                               \"fName\": \"Mukul\",\r\n                               \"lName\": \"Billing\",\r\n                               \"designation\": \"Manager\",\r\n                               \"phoneNo\": \"Manager\",\r\n                               \"phoneExtension\": \"101\",\r\n                               \"faxNo\": \"101\",\r\n                               \"email\": \"mukul.singh@hotelogix.com\",\r\n                               \"mobileNo\": \"123\",\r\n                               \"gender\": \"M\",\r\n                               \"dob\": \"2016-03-01\",\r\n                               \"website\": \"www.yahoo.com\"\r\n                       }\r\n               },\r\n               \"creditCardDetail\": {\r\n                       \"nameOnCard\": \"Mukul\",\r\n                       \"cardNo\": \"4111111111111111\",\r\n                       \"cardType\": \"Visa\",\r\n                       \"expiryMonth\": \"05\",\r\n                       \"expiryYear\": \""+year+"\",\r\n                       \"cvc\": \"101\",\r\n                       \"address\": \"creditCardAddress\",\r\n                       \"country\": \"India\",\r\n                       \"state\": \"Delhi\",\r\n                       \"city\": \"Delhi\",\r\n                       \"zip\": \"20160301\"\r\n               },\r\n               \"creditLimit\": \"100\",\r\n               \"paymentTerms\": \"5\",\r\n               \"hotels\": [\r\n               {\r\n                       \"id\": \""+hotelid1+"\"\r\n               }]\r\n           }\r\n       }\r\n   }\r\n}\r\n\r\n")
					  .asJson();
			JsonNode body = responsecreateagent.getBody();
			System.out.println("createagent response::"+body);	
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmessagecreateagent()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String createagentsuccessstring;
		createagentsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last createagent success"+createagentsuccessstring);
		return createagentsuccessstring;
	}
}
