package com.EcommerseApplication.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.EcommerseApplication.BasePackage.BaseClass;
import com.EcommerseApplication.pages.AccountPage;
import com.EcommerseApplication.pages.HomePage;
import com.EcommerseApplication.pages.LogOutPage;
import com.EcommerseApplication.pages.LoginPage;
import com.EcommerseApplication.pages.SearchPage;
import com.EcommerseApplication.utilis.Log;

public class SearchTest extends BaseClass {
	public WebDriver driver;
	HomePage homepage;
	SearchPage search;
	LoginPage loginpage;	
	LogOutPage logout;
	public SearchTest()
	{
		super();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String broswername)
	{
		Log.info("Browser Got launch");
		//driver= initilizeBrowser(prop.getProperty("browser"));
		driver=initilizeBrowser(broswername);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		Log.info("Browser Got closed");
	}

	@Test(priority = 1,enabled = true)
	public void Tc_001_verifySearchWithExistingProduct() {
		Log.startTestCase("verifySearchWithExistingProduct");
		homepage=new HomePage(driver);
		Log.info("Entering valid product into the Serachbox Textbox");
		homepage.SerachBox_Value(dataProp.getProperty("ValidProductdata"));
		search=homepage.SearchBtn_Click();
		Log.info("SearchButton clicked");
	    
		Log.info("Validation Start for " + "verifySearchWithExistingProduct" + "Test");
		boolean ProuctResult=search.Search_Productname();
		Assert.assertEquals(ProuctResult, true,"Validation is Not matched in Existing product");
		
		Log.info("validation End for " + "verifySearchWithExistingProduct" + "Test");
		Log.endTestCase("verifySearchWithExistingProduct");
	}
	
	@Test(priority = 2,enabled = true)
	public void Tc_002_verifySearchWithNonExistingProduct() throws InterruptedException {
		Log.startTestCase("verifySearchWithNonExistingProduct");
		HomePage homepage=new HomePage(driver);
		Log.info("Entering Invalid product into the Serachbox Textbox");
		homepage.SerachBox_Value(dataProp.getProperty("InvlidProductdata"));
		search=homepage.SearchBtn_Click();
		Log.info("SearchButton clicked");
		
		Thread.sleep(3000);
		Log.info("Validation Start for " + "verifySearchWithNonExistingProduct" + "Test");
		
		String ActualWarrningMessage=search.searchproduct_warningmsg();
		
		Assert.assertEquals(ActualWarrningMessage,dataProp.getProperty("WarningMsg_NonExistingProduct"),"Warrning message does not displayed");
		Log.info("validation End for " + "verifySearchWithNonExistingProduct" + "Test");
		Log.endTestCase("verifySearchWithNonExistingProduct");
	}
	

	@Test(priority = 3,enabled = true)
	public void Tc_003_verifySearchWithoutEntringProduct() throws InterruptedException {
		Log.startTestCase("verifySearchWithoutEntringProduct");
		homepage=new HomePage(driver);
	
		search=homepage.SearchBtn_Click();
		Log.info("SearchButton clicked");
		Thread.sleep(3000);
		
		Log.info("Validation Start for " + "verifySearchWithoutEntringProduct" + "Test");
		
		String ActualWarrningMessage=search.searchproduct_warningmsg();
		
		Assert.assertEquals(ActualWarrningMessage,dataProp.getProperty("WarningMsg_NonExistingProduct"),"no search product does not displayed");
		Log.info("validation End for " + "verifySearchWithoutEntringProduct" + "Test");
		Log.endTestCase("verifySearchWithoutEntringProduct");
	}
	
	@Test(priority = 4,enabled = false)
	public void TC_004_VeriftSearchproductAfterLogin(String email,String pass) throws InterruptedException
	{
		Log.startTestCase("TC_004_VeriftSearchproductAfterLogin");
		Log.info("Enetring username and password");
		AccountPage accountPage = loginpage.LoginMethod(email, pass);
		
		Log.info("Username and password inserted successfully");
		String accounttitle=accountPage.pagetittle_Myaccount();
		
		homepage=new HomePage(driver);
		Log.info("Entering valid product into the Serachbox Textbox");
		homepage.SerachBox_Value(dataProp.getProperty("ValidProductdata"));
		search=homepage.SearchBtn_Click();
		Log.info("SearchButton clicked");
	    
		Log.info("Validation Start for " + "TC_004_VeriftSearchproductAfterLogin" + "Test");
		boolean ProuctResult=search.Search_Productname();
		Assert.assertEquals(ProuctResult, true,"Validation is Not matched in Existing product");
		
		Log.info("validation End for " + "TC_004_VeriftSearchproductAfterLogin" + "Test");
		
		homepage.clickOnMyAccount();
		logout=homepage.Logoutoption_Click();
		
		homepage=logout.Click_on_Continue();
		Thread.sleep(5000);
		
		
		Log.endTestCase("TC_004_VeriftSearchproductAfterLogin");
	
		
		
	}
	
	@Test(priority = 5,enabled = true)
	public void Tc_006_verifySearchWithoutEntringProductForPlaceholder() throws InterruptedException {
		Log.startTestCase("Tc_005_verifySearchWithoutEntringProductForPlaceholder");
		homepage=new HomePage(driver);
	
		search=homepage.SearchBtn_Click();
		Log.info("SearchButton clicked");
		Thread.sleep(3000);
		
		Log.info("Validation Start for " + "Tc_005_verifySearchWithoutEntringProductForPlaceholder" + "Test");
		
		String ActualWarrningMessage=search.searchproduct_warningmsg();
		
		Assert.assertEquals(ActualWarrningMessage,dataProp.getProperty("WarningMsg_NonExistingProduct"),"no search product does not displayed");
		
		boolean VisiblityResult = search.SearchCriteria_textbox_visibility();
		Assert.assertEquals(VisiblityResult, true,"Search Criteria text box is not Displyed");
		
		String placeholder_text_Searchbox = search.GetSaerchTextbox_Placeholder();
		String placeholder_text_Searchcriteria=search.GetSearchCriteriaTextbox_placeholder();
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(placeholder_text_Searchbox,dataProp.getProperty("placeholder_Serach"),"Searbox place holder is not displyed");
		sa.assertEquals(placeholder_text_Searchcriteria, dataProp.getProperty("placeholder_serachcriteria"),"Searchcriteria Placeholder is not displyed");
		Log.info("validation End for " + "Tc_005_verifySearchWithoutEntringProductForPlaceholder" + "Test");
		
		
		
		Log.endTestCase("verifySearchWithoutEntringProduct");
	}
	
	@Test(priority = 6,enabled = true)
	public void TC_Sf_007_Searchusing_serachcriteria_textbox() throws InterruptedException
	{
		Log.startTestCase("TC_Sf_007_Searchusing_serachcriteria_textbox");
		homepage=new HomePage(driver);
	
		search=homepage.SearchBtn_Click();
		Log.info("SearchButton clicked");
		Thread.sleep(3000);
		
		Log.info("Validation Start for " + "TC_Sf_007_Searchusing_serachcriteria_textbox" + "Test");
		
		String ActualWarrningMessage=search.searchproduct_warningmsg();
		
		Assert.assertEquals(ActualWarrningMessage,dataProp.getProperty("WarningMsg_NonExistingProduct"),"no search product does not displayed");
		boolean SearchCriteria_TextboxResult = search.SearchCriteria_textbox_visibility();
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(SearchCriteria_TextboxResult,true,"Search Criteria textbox is visible ");
		
		Log.info("Entering valid product into the SerachCriteria Textbox");
		search.SaerchCriteria_Text_ValueInsert(dataProp.getProperty("ValidProductdata"));
		Log.info("Click on Search Button of crietria search");
		search.click_Btnsearch();
		
		Log.info("Validation Start for " + "TC_Sf_007_Searchusing_serachcriteria_textbox" + "Test");
		
		boolean ProuctResult=search.Search_Productname();
		Assert.assertEquals(ProuctResult, true,"Validation is Not matched in Existing product");
		
		Log.info("validation End for " + "TC_Sf_007_Searchusing_serachcriteria_textbox" + "Test");
		Log.endTestCase("TC_Sf_007_Searchusing_serachcriteria_textbox");
		
		
		
		
	}
	
	
	
}
