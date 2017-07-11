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

public class Getratestochange
{
	
	static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate;
	
	public void Getratestochangecall(String s)
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
			System.out.println("wsauthkey in getratestochange:"+keyw);

		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getratestochange:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate = CommonConfig.nightauditdate;
			
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcall("login");
			String editbookingtempid = editbookingobj.extractingtempid();
			System.out.println("editbooking temp id in getratestochange"+editbookingtempid);
			
			/*Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall("login");
			String mainid = getbookingsobj.extractingmainid();
			System.out.println("getbookings mainid in getratestochange"+mainid);*/
			
			HttpResponse<JsonNode> responsegetratestochange = Unirest.post(""+serverurl+"/ws/web/getratestochange")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "518f9cae-f825-9a76-f007-a274056704f2")
					  .body("{\r\n \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n   \"datetime\": \"2012-01-16T10:10:15\",\r\n   \"request\": {\r\n     \"method\": \"getratestochange\",\r\n     \"key\": \""+accesskey+"\",\r\n     \"data\": {\r\n       \"bookings\": [\r\n         {\r\n           \"id\": \""+editbookingtempid+"\",\r\n           \"fromDate\":\""+nightauditdate+"\",\r\n           \"toDate\":\""+nightauditdate+"\"\r\n           \r\n         }\r\n       ]\r\n     }\r\n   }\r\n }\r\n}")
					  .asJson();
			JsonNode body = responsegetratestochange.getBody();
			responseJSONString = body.toString();
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}//End of Getratestochangecall() method
	
	public String extractingmessagegetratestochange()
	{
		try{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getratestochangestring;
		getratestochangestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getratestochange success :"+getratestochangestring);
		return getratestochangestring;
		}
		
		finally
		{
			System.out.println("Getratestochange finally block ::");
			Commiteditbooking commiteditbookingobj = new Commiteditbooking();
			commiteditbookingobj.Commiteditbookingcall("login");
			
		}
		
	}// End of extractingmessagegetratestochange() method
}
