package com.EcommerseApplication.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.EcommerseApplication.BasePackage.BaseClass;
import com.EcommerseApplication.pages.HomePage;
import com.EcommerseApplication.pages.SearchPage;
import com.EcommerseApplication.utilis.Log;

public class SearchTest extends BaseClass {
	public WebDriver driver;
	HomePage homepage;
	SearchPage search;
	
	public SearchTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp()
	{
		Log.info("Browser Got launch");
		driver= initilizeBrowser(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		Log.info("Browser Got closed");
	}

	@Test(priority = 1,enabled = true)
	public void verifySearchWithExistingProduct() {
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
	public void verifySearchWithNonExistingProduct() throws InterruptedException {
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
	public void verifySearchWithoutEntringProduct() throws InterruptedException {
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
	
}
