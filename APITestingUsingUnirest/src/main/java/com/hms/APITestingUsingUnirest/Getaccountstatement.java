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

public class Getaccountstatement
{
static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Getaccountstatementcall(String s)
	{
		keytype = s;
		System.out.println("keytype:"+s);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
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
			System.out.println("login key in getaccountstatement:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall("login");
			String mainid = getbookingsobj.extractingmainid();
			System.out.println("getbookings mainid in getaccountstatement"+mainid);
			
			HttpResponse<JsonNode> responsegetaccountstatement = Unirest.post(""+serverurl+"/ws/web/getaccountstatement")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9126f5a5-4b39-6e8f-f5c2-d9417dd2c0cd")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getaccountstatement\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \"id\": \""+mainid+"\",\r\n        \"type\":\"S\",\r\n        \"expectFdFolios\": true\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responsegetaccountstatement.getBody();
			responseJSONString = body.toString();
			System.out.println("getaccountstatement response"+responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Getaccountstatementcall() method

	public String extractingmessagegetaccountstatement()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getaccountstatementstring;
		getaccountstatementstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last closeeditbooking success :"+getaccountstatementstring);
		return getaccountstatementstring;
	} // extractingmessagegetaccountstatement() method
}
