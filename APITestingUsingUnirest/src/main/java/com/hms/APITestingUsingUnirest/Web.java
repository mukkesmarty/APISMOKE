package com.hms.APITestingUsingUnirest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
//import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
public class Web
{
	@Test
	public void wsauth()
	{
		
	try {
		HttpResponse<JsonNode> response1 = Unirest.post("http://livestable.hx.local/ws/web/wsauth")
				  .header("content-type", "application/json")
				  .header("x-ig-sg", "D_gg%fkl85_j")
				  .header("cache-control", "no-cache")
				  .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
				  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"wsauth\",\r\n      \"key\": \"FB89960E9272CBA804A40D342685D78430FFEBEA\"\r\n    }\r\n  }\r\n }")
				  .asJson();
		
		JsonNode body = response1.getBody();
		System.out.println(body);
		
		//converting to gson for extracting keys
		Gson gson = new Gson();
		String responseJSONString = response1.getBody().toString();
		
		
		String[] s1=responseJSONString.split("message",0);
		System.out.println("hello string");
		System.out.println(s1[1]);
		String straftersplit =s1[1];
		System.out.println(straftersplit);
		String finalstring = straftersplit.substring(3, 10);
		System.out.println(finalstring);
		String outputstring = "success";
		if(finalstring.equals(outputstring) )
		{
			System.out.println("testcase passed:: success found");
			
		} 
		
		/*
		Pattern pattern = Pattern.compile(responseJSONString);
		Matcher matcher = pattern.matcher("McomeY");
		System.out.println("input string matches regex::="+matcher.matches());
		// bad regular expression
		pattern = Pattern.compile("*come*");
		*/
		
		/*
		//MyResponseObject myObject = gson.fromJson(responseJSONString,String.class );
		Web web = gson.fromJson(responseJSONString, Web.class);
		
		System.out.println("hi"+responseJSONString);
		System.out.println("hello"+web);
		
		JsonElement json = gson.fromJson(responseJSONString, JsonElement.class);
        String jsonInString = gson.toJson(json);
        System.out.println("jsonclass"+jsonInString);
        */
        
     // character array
        
       
       
        
       /* //converting json to map
        
        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String, Object>) gson.fromJson(json, map.getClass());
       */
        
        
        /*JSONObject jsonObj = new JSONObject();
        String hotelogix = jsonObj.getString("hotelogix");
        System.out.println(hotelogix);*/
      //  System.out.println("banno");
        
      /* JSONObject jobj = new Gson().fromJson(responseJSONString, JSONObject.class);
       String result = jobj.get("request").toString();
       System.out.println("mukke");
       //System.out.println(result);*/
        
       // char[] chararr = new char[300];
        
       // jsonInString.getChars(150, 210, chararr, 0);
        //getChars(srcBegin, srcEnd, dst, dstBegin)
        
        
        //System.out.println("mukesh array::"+chararr[1]);
        
        /*for(int i=0; i<500;  i++)
        {
     	   System.out.println(chararr[i]);
        }
        
        */
        
		
		
	} catch (UnirestException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
//////////// trying with string with java regular expression
		
	/*	try {
			HttpResponse<String> response = Unirest.post("http://livestable.hx.local/ws/web/wsauth")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"wsauth\",\r\n      \"key\": \"FB89960E9272CBA804A40D342685D78430FFEBEA\"\r\n    }\r\n  }\r\n }")
					  .asString();
			
			String strbody = response.getBody();
			System.out.println(strbody);
			
			System.out.println("using string matches method:"+strbody.matches("^accesskey$"));
			
		    }
		
		catch (UnirestException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
	
	}
	
		
		
	
	
}
