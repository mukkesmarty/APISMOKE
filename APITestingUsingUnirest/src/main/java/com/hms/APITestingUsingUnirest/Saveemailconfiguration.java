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

public class Saveemailconfiguration
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Saveemailconfigurationcall(String s)
	{
		/*keytype = s;
		if(keytype == "wsauth")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);
		}
		
		else if(keytype == "login")
		{
			Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;
		}*/
		
		keytype = s;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello if getposproduct:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in saveemailconfiguration:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in saveemailconfiguration:"+keyl);
				accesskey = keyl;
			}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			
			HttpResponse<JsonNode> responsesaveemailconfiguration = Unirest.post(""+serverurl+"/ws/web/saveemailconfiguration")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "31d793b3-01b0-1578-ef83-0ae3a156c80a")
					  .body("{\r\n\"hotelogix\": {\r\n\"version\": \"1.0\",\r\n\"datetime\": \"2012-01-16T10:11:15\",\r\n\"request\": {\r\n\"method\": \"saveemailconfiguration\",\r\n\"key\": \""+accesskey+"\",\r\n\"data\": {\r\n\"host\": \"smtp.gmail.com\",\r\n\"auth\":true,\r\n\"port\":\"465\",\r\n\"username\":\"mukesh.kumar@hotelogix.com\",\r\n\"password\":\"hotelogix@123\",\r\n\"encrptionType\":\"ssl\",\r\n\"hotels\": [\r\n{ \"id\": \""+hotelid1+"\" }\r\n]\r\n}\r\n}\r\n}\r\n}")
					  .asJson();
			JsonNode body = responsesaveemailconfiguration.getBody();
			System.out.println("saveemailconfiguration response:"+body);
			responseJSONString = body.toString();
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of saveemailconfigurationcall() method
	
	public String extractingmessagesaveemailconfiguration()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String saveemailconfigurationstring;
		saveemailconfigurationstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last saveemailconfiguration success"+saveemailconfigurationstring);
		return saveemailconfigurationstring;
	}
}
