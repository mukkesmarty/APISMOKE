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

public class Closeeditbooking
{
	
	static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Closeeditbookingcall(String s)
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
			System.out.println("login key in closeeditbooking:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcall("login");
			String editbookingtempid = editbookingobj.extractingtempid();
			System.out.println("editbooking temp id in closeeditbooking:"+editbookingtempid);
			
			HttpResponse<JsonNode> responsecloseeditbooking = Unirest.post(""+serverurl+"/ws/web/closeeditbooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "5d16b10b-98ee-cd0c-b705-ee39f98ad241")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"closeeditbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\":\"S\",\r\n         \"id\":\""+editbookingtempid+"\"\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responsecloseeditbooking.getBody();
			responseJSONString = body.toString();
			System.out.println("closeeditbooking response"+responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of Closeeditbookingcall() method
	
	public String extractingmessagecloseeditbooking()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String closeeditbookingstring;
		closeeditbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last closeeditbooking success :"+closeeditbookingstring);
		return closeeditbookingstring;
	} // extractingmessagecloseeditbooking() method
}
