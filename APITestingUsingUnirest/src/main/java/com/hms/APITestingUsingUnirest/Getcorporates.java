package com.hms.APITestingUsingUnirest;

import java.util.UUID;

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




public class Getcorporates
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Getcorporatescall(String s)
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
			System.out.println("wsauthkey in addtocart:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;*/
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in addtocart:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			String uniqueID = UUID.randomUUID().toString();
			String uniqueID1 = uniqueID+"@hotelogix.com";
			HttpResponse<JsonNode> responsegetcorporates = Unirest.post(""+serverurl+"/ws/web/getcorporates")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "033a56f4-220f-a448-8903-b9e03d291463")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:11:15\",\r\n    \"request\": {\r\n      \"method\": \"getcorporates\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n          \"hotels\": [\r\n            { \"id\": \""+hotelid1+"\" }\r\n          ]\r\n      }\r\n    }\r\n  }\r\n}\r\n\r\n")
					  .asJson();
			
			JsonNode body = responsegetcorporates.getBody();
			System.out.println("getcorporates response::"+body);
			System.out.println("huma"+uniqueID);
			System.out.println("huma"+uniqueID1);
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}		
	}// end of Getcorporatescall()
	
	public String extractingmessagegetcorporates()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getcorporatesssuccessstring;
		getcorporatesssuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last getusers success"+getcorporatesssuccessstring);
		return getcorporatesssuccessstring;
	}// End of extractingmessagegetcorporates() methodd
	
	public String extractingcorporateids()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray corporateArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels").getJSONObject(0).getJSONArray("corporates");
		JSONObject corporateid = corporateArray.getJSONObject(0);
		System.out.println(corporateid.get("id"));
		String cid = corporateid.getString("id");
		System.out.println(cid);
		String getcorporateidstring = cid;
		return getcorporateidstring;
	}// End of extractingcorporateids() method
	
	
}
