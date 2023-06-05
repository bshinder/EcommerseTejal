package com.EcommerseApplication.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.EcommerseApplication.BasePackage.BaseClass;
import com.EcommerseApplication.pages.AccountPage;
import com.EcommerseApplication.pages.ForgottenPasswordPage;
import com.EcommerseApplication.pages.HomePage;
import com.EcommerseApplication.pages.LoginPage;
import com.EcommerseApplication.utilis.utilities;

public class loginTest  extends BaseClass{
  
	public WebDriver driver;
	LoginPage loginpage;
	
	
	public loginTest() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		  
		driver=initilizeBrowser(prop.getProperty("browser"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
	    loginpage = homePage.SelectLoginOpetion();
	}
	
	@AfterMethod
	public void tearDown()
	{
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
		AccountPage accountPage = loginpage.LoginMethod(email, pass);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInfoOpetion(),"Edit Your Account Information is not Displayed");
		
	}
	@Test(priority = 2,enabled = true)
	public void verifyLoginWithInvalidCredentials() {
		
		loginpage.EnterEmailAddress(utilities.generateEmailWithTimeStamp());
		loginpage.EnterPassword(dataProp.getProperty("InvalidPassword"));
		loginpage.ClickOnLogin();
        String actualwarnningMessage = loginpage.RetriveWarnningEmailPassworNotMatchingText();
        String expectedwarnningMessage = dataProp.getProperty("expectedwarnningMessage_invalidcredential");
        Assert.assertTrue(actualwarnningMessage.contains(expectedwarnningMessage),"Expected warring message not displayed");
		
	}
	
	@Test(priority = 3, enabled = true)
	public void verifyLoginWithValidEmailInvalidPassword()
	{
		
	    loginpage.EnterEmailAddress(prop.getProperty("validUsername"));
		loginpage.EnterPassword("tejal");
		loginpage.ClickOnLogin();
        String actualwarnningMessage =loginpage.RetriveWarnningEmailPassworNotMatchingText();
        String expectedwarnningMessage = dataProp.getProperty("expectedwarnningMessage_invalidcredential");
        Assert.assertTrue(actualwarnningMessage.contains(expectedwarnningMessage),"Expected warring message not displayed");
		
	}
	

	@Test(priority = 4, enabled = true)
	public void verifyLoginWithInvalidEmailValidPassword()
	{
		
		loginpage.EnterEmailAddress(utilities.generateEmailWithTimeStamp());
		loginpage.EnterPassword(prop.getProperty("ValidPassword"));
		loginpage.ClickOnLogin();
        String actualwarnningMessage =loginpage.RetriveWarnningEmailPassworNotMatchingText();
        String expectedwarnningMessage =dataProp.getProperty("expectedwarnningMessage_invalidcredential");
        Assert.assertTrue(actualwarnningMessage.contains(expectedwarnningMessage),"Expected warring message not displayed");
		
	}
	
	@Test(priority = 5, enabled = true)
	public void verifyLoginWithBlankEmailPassword()
	{
		
		loginpage.EnterEmailAddress("");
		loginpage.EnterPassword("");
		loginpage.ClickOnLogin();
        String actualwarnningMessage = loginpage.RetriveWarnningEmailPassworNotMatchingText();
        String expectedwarnningMessage =dataProp.getProperty("expectedwarnningMessage_invalidcredential");
        Assert.assertTrue(actualwarnningMessage.contains(expectedwarnningMessage),"Expected warring message not displayed");
		
	}
	
	
	@Test(priority = 6,enabled = true,dataProvider = "validCerdintialsupplier")
	public void Login(String email,String pass,String res)
	{ 
		AccountPage accountPage=new AccountPage(driver);
		
		loginpage.EnterEmailAddress(email);
		loginpage.EnterPassword(pass); 
		loginpage.ClickOnLogin();
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
	}
	
	@Test(priority = 7,enabled = true)
	public void TC_LF_006_Forgottenpassword()
	{
		SoftAssert sa=new SoftAssert();
		boolean Resultforgotlink = loginpage.ForgottenPassword_visible();
		ForgottenPasswordPage forgottenpass = loginpage.Click_Forgottenpassword();
		sa.assertEquals(Resultforgotlink,true,"Link is not displyed");
		sa.assertEquals(forgottenpass,dataProp.getProperty("Pagetittle"),"Actual and expected tittle is not same");
		
	}
	
	@Test(priority = 8,enabled = true)
	public void TC_LF_008_PlaceholderVisibilityTest() throws InterruptedException
	{
		String usernameplaceholder_text=loginpage.Get_placeholdername_username();
		String passwordplaceholder_text=loginpage.get_placeholder_password();
		
		Thread.sleep(5000);
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(usernameplaceholder_text,dataProp.getProperty("placeholder_username"),"Placeholder text of username is not displyed");
		sa.assertEquals(passwordplaceholder_text,dataProp.getProperty("placeholder_password"),"Placeholder text of password is not displyed");
		
	}
	
	
	
	
	
}
