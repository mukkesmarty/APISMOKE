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



public class Edituser
{
	static String responseJSONString;
    String serverurl;
    String hotelid1;
    String keytype; 
    String accesskey;
    
    public void Editusercall(String s)
    {
    	System.out.println("welcome to edituser");
    	keytype = s;
    	System.out.println("check parameter::"+s);
    	
    	
    	if(keytype == "wsauth")
		{
    		/*System.out.println("this is wsauth");
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;*/
    		String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in addtocart:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*System.out.println("this is login");
			Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;*/
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in addtocart:"+keyl);
			accesskey = keyl;
		}
    	
    	
    	
    	try
    	{
    		System.out.println("welcome to try block");
    		Getcounters objgetcounters = new Getcounters();
        	objgetcounters.Getcounterscall();
        	String counter = objgetcounters.extractingdefaultcountername();
        	System.out.println("counter name:"+counter);
        	
        	System.out.println("helllo again");
        	System.out.println("hello::"+counter);
        	System.out.println("welcome to try block1");
        	
        	Getusertypes objgetusertypes = new Getusertypes();
        	objgetusertypes.Getusertypescall(keytype);
        	String usertypetitle = objgetusertypes.extractingusertypetitle();
        	System.out.println("welcome to try block2");
        	
        	Getusers objgetusers = new Getusers();
        	objgetusers.Getuserscall(keytype);
        	String userid = objgetusers.extractinguserids();
    		
    		System.out.println("edituser inside try");
    		serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			String uniqueID = UUID.randomUUID().toString();
			String uniqueID1 = uniqueID+"@hotelogix.com";
			
			
						
			System.out.println("after first scection in try block");
			
    		HttpResponse<JsonNode> responseedituser = Unirest.post(""+serverurl+"/ws/web/edituser")
    				  .header("content-type", "application/json")
    				  .header("x-ig-sg", "D_gg%fkl85_j")
    				  .header("cache-control", "no-cache")
    				  .header("postman-token", "7a577b90-6cee-bef1-7da6-199ebe7e456d")
    				  .body("{\r\n     \"hotelogix\": {\r\n       \"version\": \"1.0\",\r\n       \"datetime\": \"2012-01-16T10:11:15\",\r\n       \"request\": {\r\n         \"method\": \"edituser\",\r\n         \"key\": \""+accesskey+"\",\r\n         \"data\": {\r\n           \"userTypeTitle\": \""+usertypetitle+"\",\r\n           \"employeeId\":\""+uniqueID+"\",\r\n           \"counters\": [\r\n             {\r\n               \"name\": \""+counter+"\"\r\n             }\r\n           ],\r\n           \"fName\": \"Mukesh\",\r\n           \"lName\": \"Jha\",\r\n           \"email\": \""+uniqueID1+"\",\r\n           \"phone\": \"9760343371\",\r\n           \"mobile\": \"8802640811\",\r\n           \"country\": \"IN\",\r\n           \"state\": \"UP\",\r\n           \"city\": \"Delhi\",\r\n           \"zip\": \"20031\",\r\n           \"gender\": \"M\",\r\n           \"designation\": \"qa engineer\",\r\n           \"password\": \"111111\",\r\n           \"status\": \"A\",\r\n           \"hotels\": [\r\n             {\r\n               \"id\": \""+hotelid1+"\",\r\n               \"userIds\": [\r\n                 \""+userid+"\"\r\n               ]\r\n             }\r\n           ]\r\n         }\r\n       }\r\n     }\r\n   }")
    				  .asJson();
    		
    		JsonNode body = responseedituser.getBody();
			System.out.println("edituser response::"+body);
			//System.out.println("huma"+uniqueID);
			//System.out.println("huma"+uniqueID1);
			responseJSONString = body.toString();
    	}//end of try block
    	
    	catch(UnirestException e)
    	{
    		e.printStackTrace();
    	}// end of catch block
    	
    }// end of editusercall
    
    public String extractingmessageedituser()
    {
    	String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String edituserssuccessstring;
		edituserssuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last getusers success"+edituserssuccessstring);
		return edituserssuccessstring;
    }
}
