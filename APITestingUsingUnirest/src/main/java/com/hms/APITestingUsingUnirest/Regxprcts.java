package com.hms.APITestingUsingUnirest;

import java.util.regex.*;

public class Regxprcts
{
	public static void main(String[] args)
	{
		
		String mystr = "Welcome-to-Tutorialspoint.com";
		String st2= "";
		String[] s1=mystr.split("co",0);
		System.out.println(mystr);
		for(int i=0; i<10;i++)
		{
		System.out.println(s1[i]);
		}
		
		Pattern pattern = Pattern.compile(mystr);
		Matcher matcher = pattern.matcher("McomeY");
		System.out.println("input string matches regex::="+matcher.matches());
		// bad regular expression
		pattern = Pattern.compile("*come*");
	}
}
