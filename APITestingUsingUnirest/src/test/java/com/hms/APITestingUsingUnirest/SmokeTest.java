package com.hms.APITestingUsingUnirest;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class SmokeTest
{
	
	///////////////////////////////////-*-START OF 'FRONTDESK' SECTION  -*-////////////////////////////////
	//This is the starting of "frontdesk" group. In this section we put all the test cases of "frontdesk" here 
	
  @Test(groups = { "exzceptfrontdeskview" })
  public void UC_FD_1_TC_1_login()
  {
	  //Login loginobj = new Login();
	  //Getcounters getcountersobj = new Getcounters();
	  //Wsauth wsauthobj = new Wsauth();
	  //wsauthobj.Wsauthcall();
	  //getcountersobj.Getcounterscall();
	  
	  
	    /*loginobj.Logincall();
		String s1 = loginobj.extractingmessagelogin();
		Assert.assertEquals(s1, "success");*/
	  
	  Login loginobj = new Login();
	  loginobj.Logincall();
	  String s1 = loginobj.extractingmessagelogin();
	  Assert.assertEquals(s1, "success");
	  
	  
	  /*Logout logoutobj = new Logout();
	  logoutobj.Logoutcall();
	  String s1 = logoutobj.extractingmessageLogout();
	  Assert.assertEquals(s1, "success");
	  System.out.println("passss");*/
	  
	  
  }  
  
  /*@BeforeMethod
  public void beforeMethod()
  {
	  
  }

  @AfterMethod
  public void afterMethod()
  {
	  
  }*/
  @Test(groups = {"frontdeskcreate"})
  public void UC_FD_3_TC_1_editbooking ()
  {
	  Editbooking editbookingobj = new Editbooking();
	  editbookingobj.Editbookingcall("login");
	  String s1 = editbookingobj.extractingmessageeditbooking();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"frontdeskcreate"})
  public void UC_FD_4_TC_1_commitedbooking()
  {
	  Commiteditbooking commiteditbookingobj = new Commiteditbooking();
	  commiteditbookingobj.Commiteditbookingcall("login");
	  String s1 = commiteditbookingobj.extractingmessagecommiteditbooking();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"frontdeskcreate"})
  public void UC_FD_5_TC_1_closeeditbooking()
  {
	  Closeeditbooking closeeditbookingobj = new Closeeditbooking();
	  closeeditbookingobj.Closeeditbookingcall("login");
	  String s1 = closeeditbookingobj.extractingmessagecloseeditbooking();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"frontdeskview"})
  public void UC_FD_9_TC_1_getroomtypestochange()
  {
	  Getroomtypestochange getroomtypestochangeobj = new Getroomtypestochange();
	  getroomtypestochangeobj.Getroomtypestochangecall("login");
	  String s1 = getroomtypestochangeobj.extractingmessagegetroomtypestochange();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups= {"frontdeskview"})
  public void UC_FD_13_TC_1_getusers()
  {
	  Getusers getusersobj = new Getusers();
	  getusersobj.Getuserscall("wsauth");
	  String s1 = getusersobj.extractingmessagegetusers();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"frontdeskview"})
  public void UC_FD_14_TC_1_getdnrs()
  {
	  Getdnrs getdnrsobj = new Getdnrs();
	  getdnrsobj.Getdnrscall("login");
	  //getdnrsobj.extractingdnrid();
	  String s1 = getdnrsobj.extractingmessagegetdnrs();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"frontdeskview"})
  public void UC_FD_15_TC_1_gethousestatus()
  {
	  Gethousestatus gethousestatusobj = new Gethousestatus();
	  gethousestatusobj.Gethousestatuscall("login");
	  String s1 = gethousestatusobj.extractingmessagegethousestatus();
	  gethousestatusobj.extractingroomtypeids();
	  gethousestatusobj.extractingunassignroomsid();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"frontdeskview"})
  public void UC_FD_16_TC_1_gethoteldata()
  {
	  Gethoteldata gethoteldataobj = new Gethoteldata();
	  gethoteldataobj.Gethoteldatacall("login");
	  String s1 = gethoteldataobj.extractingmessagegethoteldata();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"frontdeskview"})
  public void UC_FD_17_TC_1_getcheckins()
  {
	  Getcheckins getcheckinsobj = new Getcheckins();
	  getcheckinsobj.Getcheckinscall("login");
	  String s1 = getcheckinsobj.extractingmessagegetcheckins();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"frontdeskview"})
  public void UC_FD_18_TC_1_getcheckouts()
  {
	  Getcheckouts getcheckoutsobj = new Getcheckouts();
	  getcheckoutsobj.Getcheckoutscall("login");
	  String s1 = getcheckoutsobj.extractingmessagegetcheckouts();
	  Assert.assertEquals(s1, "success");
  }
  
  //@BeforeTest(groups = {"tst"})
  @BeforeSuite(alwaysRun = true)
  public void beforesuite()
  {
	  System.out.println("hello beforesuite");
	Login loginobj = new Login();
	loginobj.Logincall();
	loginobj.extractingLoginKey();
	String s1 = loginobj.extractingmessagelogin();
	Assert.assertEquals(s1, "success");
  }
  
  /*@AfterSuite(alwaysRun = true)
  public void aftersuite()
  {
	  System.out.println("hello aftersuite");
	  Logout logoutobj = new Logout();
	  logoutobj.Logoutcall();
	  String s1 = logoutobj.extractingmessageLogout();
	  //Assert.assertEquals(s1, "success");
	  System.out.println(s1);
  }*/
  
  @Test(groups = {"frontdeskview"})
  public void UC_FD_19_TC_1_getbookings()
  {
	  System.out.println("hello test");
	  Getbookings getbookingsobj = new Getbookings();
	  getbookingsobj.Getbookingscall("login");
	  String s1 =getbookingsobj.extractingmessagegetbookings();
	  getbookingsobj.extractingcode();
	  getbookingsobj.extractinggroupcode();
	  Assert.assertEquals(s1,"success");
  }
  
  @Test(groups = {"frontdeskview"})
  public void UC_FD_20_TC_1_getbookingsearch()
  {
	  Getbookingsearch getbookingsearchobj = new Getbookingsearch();
	  getbookingsearchobj.Getbookingsearchcall("login","singlereserve");
	  String s1 = getbookingsearchobj.extractingmessagegetbookingsearch();
	  Assert.assertEquals(s1, "success");
  }
   
 @Test(groups = {"frontdeskview"})
  public void UC_FD_21_TC_1_getbooking()
  {
	  Getbooking getbookingobj = new Getbooking();
	  getbookingobj.getbookingcall("login");
	  String s1 = getbookingobj.extractingmessagegetbooking();
	  Assert.assertEquals(s1, "success");
  }
 
 @Test(groups = {"frontdeskview"})
 public void UC_FD_22_TC_1_getgroup()
 {
	 Getgroup getgroupobj = new Getgroup();
	 getgroupobj.Getgroupcall("login");
	 String s1 = getgroupobj.extractingmessagegetgroup();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"frontdeskview"})
 public void UC_FD_23_TC_1_getaccountstatements()
 {
	 Getaccountstatement getaccountstatementobj = new Getaccountstatement();
	 getaccountstatementobj.Getaccountstatementcall("login");
	 String s1 = getaccountstatementobj.extractingmessagegetaccountstatement();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"frontdeskview"})
 public void UC_FD_24_TC_1_getinvoices()
 {
	 Getinvoices getinvoicesobj = new Getinvoices();
	 getinvoicesobj.Getinvoicescall("login","singlecheckin");
	 String s1 = getinvoicesobj.extractingmessagegetinvoices();
	 //getinvoicesobj.extractinginvoiceid();
	 String s2 = getinvoicesobj.extractinginvoiceid();
	 Assert.assertEquals(s1, "success");
	 
	 /*Getbookingsearch getbookingsearchobj = new Getbookingsearch();
	 getbookingsearchobj.Getbookingsearchcall("login","singlereserve");
	 String s1 = getbookingsearchobj.extractingmessagegetbookingsearch();
	 Assert.assertEquals(s1, "success");*/
	 
	 
 }
 
@Test(groups = {"frontdeskview"})
 public void UC_FD_25_TC_1_getinvoice()
 {
	 Getinvoice getinvoiceobj = new Getinvoice();
	 getinvoiceobj.Getinvoicecall("login");
	 String s1 = getinvoiceobj.extractingmessagegetinvoice();
	 Assert.assertEquals(s1, "success");
	
	
	/*Getbookingsearch getbookingsearchobj = new Getbookingsearch();
	getbookingsearchobj.Getbookingsearchcall("login");*/
	
 }

@Test(groups = {"frontdeskview"})
public void UC_FD_26_TC_1_getearlycheckincharge()
{
	Getearlycheckincharge getearlycheckinchargeobj = new Getearlycheckincharge();
	getearlycheckinchargeobj.getearlycheckinchargecall("login", "singlereserve");
	String s1 = getearlycheckinchargeobj.extractingmessagegetearlycheckincharge();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdeskview"})
public void UC_FD_27_TC_1_getearlycheckoutcharge()
{
	Getearlycheckoutcharge getearlycheckoutchargeobj = new Getearlycheckoutcharge();
	getearlycheckoutchargeobj.Getearlycheckoutchargecall("login");
	String s1 = getearlycheckoutchargeobj.extractingmessagegetearlycheckoutcharge();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdeskview"})
public void UC_FD_29_TC_1_getratestochange()
{
	Getratestochange getratestochangeobj = new Getratestochange();
	getratestochangeobj.Getratestochangecall("login");
	String s1 = getratestochangeobj.extractingmessagegetratestochange();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdeskview"})
public void UC_FD_30_TC_1_getgroupcancellationcharge()
{
	Getgroupcancelcharge getgroupcancelchargeobj = new Getgroupcancelcharge();
	getgroupcancelchargeobj.Getgroupcancelchargecall("login","G");
	String s1 = getgroupcancelchargeobj.extractingmessagegetgroupcancellationcharge();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdeskview"})
public void UC_FD_31_TC_1_getdepositamount()
{
	Getdepositamount getdepositamountobj = new Getdepositamount();
	getdepositamountobj.Getdepositamountcall("login");
	String s1 = getdepositamountobj.extractingmessagegetdepositamount();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"tst11"})
public void UC_FD_34_TC_1_unassignroom()
{
	Unassignroom unassignroomobj = new Unassignroom();
	try {
		unassignroomobj.unassignroomcall("login");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String s1 = unassignroomobj.extractingmessageunassignroom();
	Assert.assertEquals(s1, "success");
}


@Test(groups = {"frontdeskcreate"})
public void UC_FD_37_TC_1_movebooking()
{
	/*Getroomtypestochange getroomtypestochangeobj = new Getroomtypestochange();
	getroomtypestochangeobj.Getroomtypestochangecall("login");
	getroomtypestochangeobj.extractingmessagegetroomtypestochange();
	getroomtypestochangeobj.extractingroomtypes();*/
	
	Movebooking movebookingobj = new Movebooking();
	movebookingobj.Movebookingcall("login");
	String s1 = movebookingobj.extractingmessagemovebooking();
	Assert.assertEquals(s1, "success");
}

/*@Test(groups = {"frontdeskview"})
public void UC_FD_45_TC_1()
{
	Getallpaytypes getallpaytypesobj = new Getallpaytypes();
	getallpaytypesobj.Getallpaytypescall("login");
	String s1 = getallpaytypesobj.extractingmessageGetallpaytypes();
	System.out.println("mukesh"+s1);
	Assert.assertEquals(s1, "success");
}*/

@Test(groups = {"frontdeskcreate"})
public void UC_FD_50_TC_1_adddnrs()
{
	Adddnrs adddnrsobj = new Adddnrs();
	adddnrsobj.Adddnrscall("login");
	String s1 = adddnrsobj.extractingmessageadddnrs();
	Assert.assertEquals(s1, "success");
}
  

@Test(groups = {"frontdeskcreate"})
public void UC_FD_51_TC_1_deletednrs()
{
	Deletednrs deletednrsobj = new Deletednrs();
	deletednrsobj.Deletednrscall("login");
	String s1 = deletednrsobj.extractingmessagedeletednrs();
	Assert.assertEquals(s1, "success");
}


@Test(groups = {"frontdeskcreate"})
public void UC_FD_52_TC_1_editdnrs()
{
	Editdnrs editdnrsobj = new Editdnrs();
	editdnrsobj.Editdnrcall("login");
	String s1 = editdnrsobj.extractingmessageeditdnrs();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdesk"})
public void UC_FD_53_TC_1_keepalive()
{
	Keepalive keepaliveobj = new Keepalive();
	keepaliveobj.Keepalivecall("login");
	String s1 = keepaliveobj.extractingmessagekeepalive();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdeskcreate"})
public void UC_FD_54_TC_1_addoccupancies()
{
	Addoccupancies addoccupanciesobj = new Addoccupancies();
	addoccupanciesobj.Addoccupanciescall("login");
	String s1 = addoccupanciesobj.extractingmessageaddoccupancies();
	Assert.assertEquals(s1, "success");
}

@Test(groups ={"frontdeskcreate"})
public void UC_FD_55_TC_1_changepayterm()
{
	Changepayterm changepaytermobj = new Changepayterm();
	changepaytermobj.Changepaytermcall("login");
	String s1 = changepaytermobj.extractingmessagechangepayterm();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdeskview"})
public void UC_FD_56_TC_1_checknightauditstatus()
{
	Checknightauditstatus Checknightauditstatusobj = new Checknightauditstatus();
	Checknightauditstatusobj.Checknightauditstatuscall("login");
	String s1 = Checknightauditstatusobj.extractingmessagecheckingnightauditstatus();
	Assert.assertEquals(s1, "Night Audit completed and current date of the system is");
}

@Test(groups = {"frontdeskcreate"})
public void UC_FD_57_TC_1_consolidateaccount()
{
	Consolidateaccount consolidateaccountobj = new Consolidateaccount();
	consolidateaccountobj.Consolidateaccountcall("login", "S");
	String s1 = consolidateaccountobj.extractingmessageconsolidteaccount();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdeskcreate"})
public void UC_FD_58_TC_1_deleteproformainvoice()
{
	Deleteproformainvoice deleteproformainvoiceobj = new Deleteproformainvoice();
	deleteproformainvoiceobj.Deleteproformainvoicecall("login", "G");
	String s1 = deleteproformainvoiceobj.extractingmessagedeleteproformainvoice();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdeskcreate"})
public void UC_FD_59_TC_1_earlycheckout()
{
	Earlycheckout earlycheckoutobj = new Earlycheckout();
	earlycheckoutobj.Earlycheckoutcall("login", "S");
}

@Test(groups = {"frontdeskview"})
public void UC_FD_70_TC_1_guestlookup()
{
	Guestlookup guestlookupobj = new Guestlookup();
	guestlookupobj.Guestlookupcall("login");
	String s1 = guestlookupobj.extractingmessageguestlookup();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"frontdeskcreate"})
public void UC_FD_84_TC_1_sendbookingemail()
{
	Sendbookingemail sendbookingemailobj = new Sendbookingemail();
	sendbookingemailobj.Senbookingemailcall("login");
	String s1 = sendbookingemailobj.extractingmessagesendbookingemail();
	Assert.assertEquals(s1, "success");
}


  ///////////////////////////////-*- END OF 'FRONTDESK' SECTION-*- ///////////////////////////////////
  
  
  
  
  
  
  
  ////////////////////////////////-*- START OF 'ADMIN' SECTION-*-////////////////////////////////////
   
  @Test(groups = {"adminview"})
  public void UC_AD_1_TC_1_wsauth()
  {
	Wsauth wsauthobj = new Wsauth();
	wsauthobj.Wsauthcall();
	String s1 = wsauthobj.extractingmessage();
	Assert.assertEquals(s1, "success");	
  }
  
  @Test(groups ={"adminview"})
  public void UC_AD_2_TC_1_login()
  {
	  Login loginobj = new Login();
	loginobj.Logincall();
	String s1 =	loginobj.extractingmessagelogin();
	Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"exception"})
  public void UC_AD_3_TC_1_logout()
  {
	Logout logoutobj = new Logout();
	logoutobj.Logoutcall();
	String s1 = logoutobj.extractingmessageLogout();
	Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"adminview"})
  public void UC_AD_4_TC_1_getusertypes()
  {
	Getusertypes getusertypesobj = new Getusertypes();
	getusertypesobj.Getusertypescall("login");
	String s2 = getusertypesobj.extractingusertypetitle();
	String s1 = getusertypesobj.extractingmessageGetusertypes();
	Assert.assertEquals(s1, "success");
	System.out.println(s2);
  }
  
  @Test(groups = {"adminview"})
  public void UC_AD_5_TC_1_getallpaytypes()
  {
	  Getallpaytypes getallpaytypesobj = new Getallpaytypes();
	  getallpaytypesobj.Getallpaytypescall("wsauth");
	  String s1 = getallpaytypesobj.extractingmessageGetallpaytypes();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"adminview"})
  public void UC_AD_6_TC_1_getcounters()
  {
	  Getcounters getcountersobj = new Getcounters();
	  getcountersobj.Getcounterscall();
	  String s1 = getcountersobj.extractingmessagegetcounters();
	  Assert.assertEquals(s1, "success");
  }
  
  
  @Test(groups = {"adminview"})
  public void UC_AD_7_TC_1_getusers()
  {
	  Getusers getusersobj = new Getusers();
	  getusersobj.Getuserscall("wsauth");
	  String s1 = getusersobj.extractingmessagegetusers();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"adminview"})
  public void UC_AD_8_TC_1_getagents()
  {
	  Getagents getagentsobj = new Getagents();
	  getagentsobj.Getagentscall("wsauth");
	  String s1 = getagentsobj.extractingmessagegetagents();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"adminview"})
  public void UC_AD_9_TC_1_getcorporates()
  {
	  Getcorporates getcorporatesobj = new Getcorporates();
	  getcorporatesobj.Getcorporatescall("wsauth");
	  String s1 = getcorporatesobj.extractingmessagegetcorporates();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"admincreate"})
  public void UC_AD_10_TC_1_createuser()
  {
	  Createuser createuserobj = new Createuser();
	  createuserobj.Createusercall("wsauth");
	String s1 = createuserobj.extractingmessagecreateuser();
	Assert.assertEquals(s1, "success");
  }
  
  @Test(groups= {"admincreate"})
  public void UC_AD_11_TC_1_edituser()
  {
	  Edituser edituserobj = new Edituser();
	  edituserobj.Editusercall("wsauth");
	  String s1 = edituserobj.extractingmessageedituser();
	  Assert.assertEquals(s1, "success");  
  }
  
  @Test(groups = {"admincreate"})
  public void UC_AD_12_TC_1_createagent()
  {
	  Createagent createagentobj = new Createagent();
	  createagentobj.Createagentcall("wsauth");
	  String s1 = createagentobj.extractingmessagecreateagent();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"admincreate"})
  public void UC_AD_13_TC_1_createcorporate()
  {
	  Createcorporate createcorporateobj = new Createcorporate();
	  createcorporateobj.Createcorporatecall("wsauth");
	  String s1 = createcorporateobj.extractingmessagecreatecorporate();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"admincreate"})
  public void UC_AD_14_TC_1_editcorporate()
  {
	  Editcorporate editcorporateobj = new Editcorporate();
	  editcorporateobj.Editcorporatecall("wsauth");
	  String s1 = editcorporateobj.extractingmessageeditcorporate();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"admincreate"})
  public void UC_AD_15_TC_1_saveemailconfiguration()
  {
	Saveemailconfiguration saveemailconfigurationobj = new Saveemailconfiguration();
	saveemailconfigurationobj.Saveemailconfigurationcall("wsauth");
	String s1 = saveemailconfigurationobj.extractingmessagesaveemailconfiguration();
	Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"admincreate"})
  public void UC_AD_16_TC_1_editpackageprice()
  {
	  Editpackageprice editpackagepriceobj = new Editpackageprice();
	  editpackagepriceobj.Editpackagepricecall("wsauth");
	  String s1 = editpackagepriceobj.extractingmessageeditpricepackage();
	  Assert.assertEquals(s1, "queued successfully");
  }
  
 @Test(groups = {"admincreate"})
  public void UC_AD_17_TC_1_opencloseoverbooking()
  {
	  Opencloseoverbooking opencloseoverbookingobj = new Opencloseoverbooking();
	  opencloseoverbookingobj.Opencloseoverbookingcall("wsauth");
	  String s1 = opencloseoverbookingobj.extractingmessageopencloseoverbooking();
	  Assert.assertEquals(s1, "success");
  }
  
  
 
////////////////////////////////-*- END OF 'ADMIN' SECTION-*-////////////////////////////////////
  
 
 
 
 
 
 
/////////////////////////////////////-* START OF 'CRS' SECTION -*-///////////////////////////////////
 
 @Test(groups = {"web"})
 public void UC_WB_1_TC_1_search()
 {
	 Search searchobj = new Search();
	 searchobj.Searchcall("wsauth");
	 String s1 = searchobj.extractingmessagesearch();
	 //String rateid = searchobj.extractingrateid();
	 //System.out.println("hola::"+rateid);
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_2_TC_1_addtocart()
 {
	 Addtocart addtocartobj = new Addtocart();
	 addtocartobj.Addtocartcall("wsauth");
	 String s1 = addtocartobj.extractingmessageaddtocart();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_3_TC_1_loadcart()
 {
	 Loadcart loadcartobj = new Loadcart();
	 loadcartobj.Loadcartcall("wsauth");
	 String s1 = loadcartobj.extractingmessageloadcart();
	 String bookingid = loadcartobj.extracingbookingid();
	 System.out.println(bookingid);
	 loadcartobj.extractinghotelid();
	 loadcartobj.extractinggueststayid();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 
 @Test(groups = {"web"})
 public void UC_WB_4_TC_1_deletefromcart()
 {
	 Deletefromcart deletefromcartobj = new Deletefromcart();
	 deletefromcartobj.Deletefromcartcall("wsauth");
	 String s1 = deletefromcartobj.extractingmessagedeletefromcart();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_6_TC_1_getaddons()
 {
	 Getaddons getaddonsobj = new Getaddons();
	 getaddonsobj.Getaddonscall("wsauth");
	 String s1 = getaddonsobj.exrtractingmessagegetaddons();
	 String s2 =getaddonsobj.extractingitemidfromrequest();
	 System.out.println(s2);
	 String s3 = getaddonsobj.extractingaddonsid();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 
 @Test(groups = {"web"})
 public void UC_WB_7_TC_1_attachaddons()
 {
	Attachaddons attachaddonsobj = new Attachaddons();
	attachaddonsobj.Attachaddonscall("wsauth");
	String s1 = attachaddonsobj.extractingmessageattachaddons();
	Assert.assertEquals(s1, "Successfull!");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_8_TC_1_savebooking()
 {
	 Savebooking savebookingobj = new Savebooking();
	 savebookingobj.Savebookingcall("wsauth");
	 String s1 = savebookingobj.extractingmessagesavebooking();
	 String s2 = savebookingobj.extractingorderid();
	 System.out.println(s2);
	 String s3 = savebookingobj.extractingdeposittotal();
	 System.out.println(s3);
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_9_TC_1_confirmbooking()
 {
	 Confirmbooking confirmbookingobj = new Confirmbooking();
	 confirmbookingobj.Confirmbookingcall("wsauth");
	 String s1 = confirmbookingobj.extractingmessageconfirmbooking();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_10_TC_1_gethoteloptions()
 {
	 Gethoteloptions gethotelooptionsobj = new Gethoteloptions();
	 gethotelooptionsobj.Gethoteloptionscall("wsauth");
	 String s1 = gethotelooptionsobj.extractingmessagegethoteloptions();
	 Assert.assertEquals(s1, "Success");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_11_TC_1_getpaytype()
 {
	 Getpaytype getpaytypeobj = new Getpaytype();
	 getpaytypeobj.Getpaytypecall("wsauth");
	 String s1 = getpaytypeobj.extractingmessagegetpaytype();
	 Assert.assertEquals(s1, "Success");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_12_TC_1_getcountrylist()
 {
	 Getcountrylist getcountrylistobj = new Getcountrylist();
	 getcountrylistobj.Getcountrylistcall("wsauth");
	 String s1 = getcountrylistobj.extractingmessgaegetcountrylist();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_13_TC_1_getzonelist()
 {
	 Getzonelist getzonelistobj = new Getzonelist();
	 getzonelistobj.Getzonelistcall("wsauth");
	 String s1 = getzonelistobj.extractingmessagegetzonelist();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_14_TC_1_bestsuggestedrate()
 {
	 Bestsuggestedrate bestsuggestedrateobj = new Bestsuggestedrate();
	 bestsuggestedrateobj.Bestsuggestedratecall("wsauth");
	 String s1 = bestsuggestedrateobj.extractingmessagebestsuggetedrate();
	 Assert.assertEquals(s1, "Success");
 }
 
 
 @Test(groups = {"web"})
 public void UC_WB_15_TC_1_getpos()
 {
	 Getpos getposobj = new Getpos();
	 getposobj.Getposcall("wsauth");
	 String s1 = getposobj.extractingmessagegetpos();
	 getposobj.extractingposid();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_16_TC_1_getposproduct()
 {
	 Getposproduct getposproductobj = new Getposproduct();
	 getposproductobj.Getposproductcall("wsauth");
	 String s1 = getposproductobj.extractingmessagegetposproduct();
	 Assert.assertEquals(s1, "success");
 }
 
 
 @Test(groups ={"web"})
 public void UC_WB_17_TC_1_bookingrestrictions()
 {
	 Bookingrestrictions bookingrestrictionsobj = new Bookingrestrictions();
	 bookingrestrictionsobj.Bookingrestrictionscall("wsauth");
	 String s1 = bookingrestrictionsobj.extractingmessagebookingrestrictions();
	 Assert.assertEquals(s1, "Success");
 }
 
 
 @Test(groups = {"web"})
 public void UC_WB_18_TC_1_occupancywiserate()
 {
	 Occupancywiserate occupancywiserateobj = new Occupancywiserate();
	 occupancywiserateobj.Occupancywiseratecall("wsauth");
	 String s1 = occupancywiserateobj.extractingmessageoccupancywiserate();
	 Assert.assertEquals(s1, "Success");
 }
 
 
 @Test(groups ={"web"})
 public void UC_WB_19_TC_1_clearcart()
 {
	 Clearcart clearcartobj = new Clearcart();
	 clearcartobj.Clearcartcall("wsauth");
	 String s1 = clearcartobj.extractingmessageclearcart();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
/////////////////////////////////////-* END OF 'CRS' SECTION -*-///////////////////////////////////
  
  
  
}
