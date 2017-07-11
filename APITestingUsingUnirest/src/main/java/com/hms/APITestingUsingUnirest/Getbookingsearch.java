package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

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



public class Getbookingsearch
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String searchstring;
	String nightauditdate;
	String nightauditdate1;
	String nightauditdate2;
	
	public void Getbookingsearchcall(String s1, String s2)
	{
		keytype = s1;
		searchstring = s2;
		
		System.out.println("keytype:"+s1);
		System.out.println("searchstring:"+s2);
		
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
			System.out.println("login key in getinvoices:"+keyl);
			accesskey = keyl;
			
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate = CommonConfig.nightauditdate;
			nightauditdate1 = CommonConfig.nightauditdate1;
			nightauditdate2 = CommonConfig.nightauditdate2;
			
			// Get the current date and time
						LocalDateTime currentTime = LocalDateTime.now();
						System.out.println("Current DateTime: " + currentTime);
						int year = Year.now().getValue()+1;
						System.out.println(year);
			
			
			HttpResponse<JsonNode> responsegetbookingsearch = Unirest.post(""+serverurl+"/ws/web/getbookingsearch")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "3aacc3e0-0b54-2894-e596-a1a371178e2f")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n     \"method\": \"getbookingsearch\",\r\n     \"key\": \""+accesskey+"\",\r\n     \"data\": {\r\n       \"fromDate\": \""+nightauditdate1+"\",\r\n       \"toDate\": \""+nightauditdate2+"\",\r\n       \"searchText\": \""+searchstring+"\"\r\n     }\r\n   }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsegetbookingsearch.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// End of Getbookingsearchcall() method 
	
	public String extractingmessagegetbookingsearch()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingsearchstring;
		getbookingsearchstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbookingsearch success:"+getbookingsearchstring);
		return getbookingsearchstring;
	}//End of extractingmessagegetbookingsearch() method

	public String extractingmainid()
	{
		System.out.println("welcome to extractingmainid for single reservation");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingsmainid;
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		JSONObject bookingsmainid = getbookingsArray.getJSONObject(0);
		getbookingsmainid = bookingsmainid.getString("mainId");
		System.out.println(":getbookings mainid:"+getbookingsmainid);
		return getbookingsmainid;
	}//End of extractingmainid() method
	
}//End of the class
