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

public class Getusertypes
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;

	
	
	public void Getusertypescall(String s)
	{
		/*System.out.println("hello :: Getusertypescall");
		Wsauth objwsauth = new Wsauth();
		objwsauth.Wsauthcall();
		String  wsauthaccesskey = objwsauth.extractingWsauthKey();
		System.out.println("hola::" +wsauthaccesskey);*/
		
		keytype = s;
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;*/
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in getusertypes:"+keyw);

			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;*/
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getusertypes:"+keyl);
			accesskey = keyl;
		}
		
		try {
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
		HttpResponse<JsonNode> responsegetusertypes = Unirest.post(""+serverurl+"/ws/web/getusertypes")
				  .header("content-type", "application/json")
				  .header("x-ig-sg", "D_gg%fkl85_j")
				  .header("cache-control", "no-cache")
				  .header("postman-token", "169d7ad5-8bde-8109-5b27-efbc1a18341c")
				  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:11:15\",\r\n    \"request\": {\r\n      \"method\": \"getusertypes\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n           \"hotels\":[\r\n           {\r\n               \"id\":"+hotelid1+"\r\n           }\r\n        ]        \r\n      }\r\n    }\r\n  }\r\n}")
				  .asJson();
		
		System.out.println("hello:: Getusertypescall"+responsegetusertypes);
		responseJSONString = responsegetusertypes.getBody().toString();
		JsonNode body = responsegetusertypes.getBody();
		System.out.println("getusertypes response::"+body);
		responseJSONString = body.toString();
		}
		
		catch (UnirestException e)
		{
			e.printStackTrace();
		}
	}
	
	public String extractingmessageGetusertypes()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getusertypessuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		return getusertypessuccessstring;
	}// End of extractingmessageGetusertypes method
	
	public String extractingusertypetitle()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
	// String getusertypestitlestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels").getJSONArray(0).getString(1);
		JSONArray userTypesArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels").getJSONObject(0).getJSONArray("userTypes");
		/*for(int i =0 ; i< userTypesArray.length(); i++)
		{
	        JSONObject userType = userTypesArray.getJSONObject(0);
	        System.out.println(userType.get("title"));
	        //JSONArray ak = userType.getJSONArray("title");
	        //System.out.println(ak);
	        String s1 =userType.getString("status");
	        System.out.println(s1);
	        
	    }*/
		
		JSONObject userType = userTypesArray.getJSONObject(0);
        System.out.println(userType.get("title"));
        String ut =userType.getString("title");
        System.out.println(ut);
		
	 //System.out.println("hola getusertypes title"+getusertypestitlestring);
		//JSONObject userType = userTypesArray.getJSONObject(0).get("title");
		//String getusertypestitlestring = userType.toString();
		String getusertypestitlestring = ut;
		return getusertypestitlestring;
		
	}
	
}
