package com.hms.APITestingUsingUnirest;

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


public class Createuser
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Createusercall(String s)
	{
		keytype = s;
		Getusertypes objgetusertypes = new Getusertypes();
		objgetusertypes.Getusertypescall(keytype);
		objgetusertypes.extractingmessageGetusertypes();
		String usertypetitle = objgetusertypes.extractingusertypetitle();
		
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
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			String uniqueID = UUID.randomUUID().toString();
			
			HttpResponse<JsonNode> responsecreateuser = Unirest.post(""+serverurl+"/ws/web/createuser")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9dacb698-68ef-b482-4465-13fcc9eee7c3")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n   \"datetime\": \"2012-01-16T10:11:15\",\r\n   \"request\": {\r\n     \"method\": \"createuser\",\r\n     \"key\": \""+accesskey+"\",\r\n     \"data\": {   \r\n        \"userTypeTitle\": \""+usertypetitle+"\",    \r\n        \"counters\" : [{\"name\":\"Default Counter\"}],\r\n        \"employeeId\": \""+uniqueID+"\",\r\n        \"fName\": \"mukeshauto\",\r\n        \"lName\": \"kumarauto\",\r\n        \"email\": \""+uniqueID+"@hotelogix.com"+"\",\r\n        \"phone\": \"8802640811\",\r\n        \"mobile\": \"8802640811\",\r\n        \"country\": \"IN\",\r\n        \"state\": \"UP\",\r\n        \"city\": \"Noida\",\r\n        \"zip\": \"20031\",\r\n        \"gender\": \"M\",\r\n        \"designation\": \"Employee\",\r\n        \"password\": \"111111\",\r\n        \"hotels\": \r\n          [\r\n            {\r\n              \"id\": \""+hotelid1+"\"\r\n            }\r\n         ]\r\n     }\r\n   }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsecreateuser.getBody();
			System.out.println("createuser response::"+body);
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}//end of Createusercall()
	
	public String extractingmessagecreateuser()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String createusersuccessstring;
		createusersuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last getusers success"+createusersuccessstring);
		return createusersuccessstring;
	}
}
