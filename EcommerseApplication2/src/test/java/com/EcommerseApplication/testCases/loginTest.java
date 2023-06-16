package com.EcommerseApplication.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.EcommerseApplication.BasePackage.BaseClass;
import com.EcommerseApplication.pages.AccountPage;
import com.EcommerseApplication.pages.ForgottenPasswordPage;
import com.EcommerseApplication.pages.HomePage;
import com.EcommerseApplication.pages.LoginPage;
import com.EcommerseApplication.utilis.Log;
import com.EcommerseApplication.utilis.utilities;

public class loginTest  extends BaseClass{
  
	public WebDriver driver;
	LoginPage loginpage;
	
	
	public loginTest() {
		super();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String broswername) {
		
		Log.info("Browser Got launched");  
		//driver=initilizeBrowser(prop.getProperty("browser"));
		driver=initilizeBrowser(broswername);
		Log.info("Broswer Launched successfully");
		HomePage homePage=new HomePage(driver);
		Log.info("Click on MyAccount Link");
		homePage.clickOnMyAccount();
		
		Log.info("Click On Login Link");
	    loginpage = homePage.SelectLoginOpetion();
	}
	
	@AfterMethod
	public void tearDown()
	{
		Log.info("Driver got Closed");
		driver.quit();
	}
	
	@DataProvider(name="validCerdintialsupplier")
	public Object[][] supplyTestData() {
		Object [][] data=utilities.getTestDataFromExcel("loginsheet");
		return data;
		
	}
	
	
	
	@Test(priority = 1, enabled = false,dataProvider = "validCerdintialsupplier")
	public void verifyLoginWithValidCredintials(String email,String pass)
	{
		Log.startTestCase("verifyLoginWithValidCredintials");
		Log.info("Enetring username and password");
		AccountPage accountPage = loginpage.LoginMethod(email, pass);
		Log.info("Username and password inserted successfully");
		Log.info("Assertion started");
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInfoOpetion(),"Edit Your Account Information is not Displayed");
		Log.info("Assertion Done");
		Log.endTestCase("verifyLoginWithValidCredintials");
	}
	@Test(priority = 2,enabled = false)
	public void verifyLoginWithInvalidCredentials() {
		
		Log.startTestCase("verifyLoginWithInvalidCredentials");
		Log.info("Enetring username");
		loginpage.EnterEmailAddress(utilities.generateEmailWithTimeStamp());
		Log.info("Enetring Invalid password");
		loginpage.EnterPassword(dataProp.getProperty("InvalidPassword"));
		Log.info("Click on login Button");
		loginpage.ClickOnLogin();
		
		Log.info("Validation stared");
        String actualwarnningMessage = loginpage.RetriveWarnningEmailPassworNotMatchingText();
        String expectedwarnningMessage = dataProp.getProperty("expectedwarnningMessage_invalidcredential");
        Assert.assertTrue(actualwarnningMessage.contains(expectedwarnningMessage),"Expected warring message not displayed");
        Log.info("validation Completeed ");
        Log.endTestCase("verifyLoginWithInvalidCredentials");
		
	}
	
	@Test(priority = 3, enabled = false)
	public void verifyLoginWithValidEmailInvalidPassword()
	{
		Log.startTestCase("verifyLoginWithValidEmailInvalidPassword");
		Log.info("Enetring valid username");
	    loginpage.EnterEmailAddress(prop.getProperty("validUsername"));
	    Log.info("Enetring Invalid password");
		loginpage.EnterPassword("tejal");
		Log.info("Click on login button");
		loginpage.ClickOnLogin();
		
		Log.info("Validation stared");
        String actualwarnningMessage =loginpage.RetriveWarnningEmailPassworNotMatchingText();
        String expectedwarnningMessage = dataProp.getProperty("expectedwarnningMessage_invalidcredential");
        Assert.assertTrue(actualwarnningMessage.contains(expectedwarnningMessage),"Expected warring message not displayed");
        Log.info("Validation Done");
        Log.endTestCase("verifyLoginWithValidEmailInvalidPassword");
		
	}
	

	@Test(priority = 4, enabled = false)
	public void verifyLoginWithInvalidEmailValidPassword()
	{
		Log.startTestCase("verifyLoginWithInvalidEmailValidPassword");
		Log.info("Enetring username");
		loginpage.EnterEmailAddress(utilities.generateEmailWithTimeStamp());
		Log.info("Enetring Invlid password");
		loginpage.EnterPassword(prop.getProperty("ValidPassword"));
		Log.info("Click on login button");
		loginpage.ClickOnLogin();
		Log.info("Validation stared");
        String actualwarnningMessage =loginpage.RetriveWarnningEmailPassworNotMatchingText();
        String expectedwarnningMessage =dataProp.getProperty("expectedwarnningMessage_invalidcredential");
        Assert.assertTrue(actualwarnningMessage.contains(expectedwarnningMessage),"Expected warring message not displayed");
        Log.info("Validation done successfully");
		Log.endTestCase("verifyLoginWithInvalidEmailValidPassword");
	}
	
	@Test(priority = 5, enabled = false)
	public void verifyLoginWithBlankEmailPassword()
	{
		Log.startTestCase("verifyLoginWithBlankEmailPassword");
		Log.info("enetring username");
		loginpage.EnterEmailAddress("");
		Log.info("Enetring password");
		loginpage.EnterPassword("");
		Log.info("click on login button");
		loginpage.ClickOnLogin();
		Log.info("Validation stared");
        String actualwarnningMessage = loginpage.RetriveWarnningEmailPassworNotMatchingText();
        String expectedwarnningMessage =dataProp.getProperty("expectedwarnningMessage_invalidcredential");
        Assert.assertTrue(actualwarnningMessage.contains(expectedwarnningMessage),"Expected warring message not displayed");
        Log.info("Validation Done Successfully");
        Log.endTestCase("verifyLoginWithBlankEmailPassword");
		
	}
	
	
	@Test(priority = 6,enabled = true,dataProvider = "validCerdintialsupplier")
	public void Login(String email,String pass,String res)
	{ 
		Log.startTestCase("Login");
		AccountPage accountPage=new AccountPage(driver);
		Log.info("Eneytring username");
		loginpage.EnterEmailAddress(email);
		Log.info("Enetring password");
		loginpage.EnterPassword(pass);
		Log.info("Click on login button");
		loginpage.ClickOnLogin();
		Log.info("validation stared");
		if(res.contentEquals("valid"))
		{
			Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInfoOpetion(),"Edit Your Account Information is not Displayed");
		}
		else if(res.contentEquals("invalid"))
		{
			String actualwarnningMessage = loginpage.RetriveWarnningEmailPassworNotMatchingText();
	        String expectedwarnningMessage =dataProp.getProperty("expectedwarnningMessage_invalidcredential");
	        Assert.assertTrue(actualwarnningMessage.contains(expectedwarnningMessage),"Expected warring message not displayed");
			
		}
		Log.info("Validation done successfully");
		Log.endTestCase("Login");
	}
	
	@Test(priority = 7,enabled = true)
	public void TC_LF_006_Forgottenpassword()
	{
		Log.startTestCase("TC_LF_006_Forgottenpassword");
		Log.info("Validation stared");
		SoftAssert sa=new SoftAssert();
		boolean Resultforgotlink = loginpage.ForgottenPassword_visible();
		ForgottenPasswordPage forgottenpass = loginpage.Click_Forgottenpassword();
		sa.assertEquals(Resultforgotlink,true,"Link is not displyed");
		sa.assertEquals(forgottenpass,dataProp.getProperty("Pagetittle"),"Actual and expected tittle is not same");
		Log.info("valodation Done successfully");
		Log.endTestCase("TC_LF_006_Forgottenpassword");
		
	}
	
	@Test(priority = 8,enabled = true)
	public void TC_LF_008_PlaceholderVisibilityTest() throws InterruptedException
	{
		Log.startTestCase("TC_LF_008_PlaceholderVisibilityTest");
		Log.info("Getting username placeholder text");
		String usernameplaceholder_text=loginpage.Get_placeholdername_username();
		Log.info("getting password placeholder text");
		String passwordplaceholder_text=loginpage.get_placeholder_password();
		
		Thread.sleep(5000);
		Log.info("Validation stared");
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(usernameplaceholder_text,dataProp.getProperty("placeholder_username"),"Placeholder text of username is not displyed");
		sa.assertEquals(passwordplaceholder_text,dataProp.getProperty("placeholder_password"),"Placeholder text of password is not displyed");
		Log.info("Validation done successfully");
		Log.endTestCase("TC_LF_008_PlaceholderVisibilityTest");
	}
	
	
	
	
	
	
}
