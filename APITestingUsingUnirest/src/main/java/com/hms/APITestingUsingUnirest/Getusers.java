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


public class Getusers
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;

	public void Getuserscall(String s)
	{
		keytype = s;
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;*/
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in getusers:"+keyw);

			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;*/
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getusers:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl= CommonConfig.serverurl;
			hotelid1= CommonConfig.hotelid1;
			
			HttpResponse<JsonNode> responsegetusers = Unirest.post(""+serverurl+"/ws/web/getusers")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9334dbf0-cd9b-ee41-248a-cf41cd62e6fe")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:11:15\",\r\n    \"request\": {\r\n      \"method\": \"getusers\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n          \"hotels\": [\r\n            { \"id\": \""+hotelid1+"\" }\r\n          ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsegetusers.getBody();
			responseJSONString = responsegetusers.getBody().toString();
			
			
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}
	
	public String extractingmessagegetusers()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getuserssuccessstring;
		getuserssuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last getusers success"+getuserssuccessstring);
		return getuserssuccessstring; 
	}// end of extractingmessagegetusers method
	
	public String extractinguserids()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray usersArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels").getJSONObject(0).getJSONArray("users");
		JSONObject userid = usersArray.getJSONObject(0);
		System.out.println(userid.get("id"));
		String uid = userid.getString("id");
		System.out.println(uid);
		String getusersidestring = uid;
		return getusersidestring;
	}
}
