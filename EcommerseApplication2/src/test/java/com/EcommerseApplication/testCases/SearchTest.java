package com.EcommerseApplication.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.EcommerseApplication.BasePackage.BaseClass;
import com.EcommerseApplication.pages.HomePage;
import com.EcommerseApplication.pages.SearchPage;

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
		driver= initilizeBrowser(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1,enabled = true)
	public void verifySearchWithExistingProduct() {
		homepage=new HomePage(driver);
		homepage.SerachBox_Value(dataProp.getProperty("ValidProductdata"));
		search=homepage.SearchBtn_Click();
	    
		boolean ProuctResult=search.Search_Productname();
		Assert.assertEquals(ProuctResult, true);
	}
	
	@Test(priority = 2,enabled = true)
	public void verifySearchWithNonExistingProduct() throws InterruptedException {
		HomePage homepage=new HomePage(driver);
		homepage.SerachBox_Value(dataProp.getProperty("InvlidProductdata"));
		search=homepage.SearchBtn_Click();
		
		Thread.sleep(3000);
		
		String ActualWarrningMessage=search.searchproduct_warningmsg();
		
		Assert.assertEquals(ActualWarrningMessage,dataProp.getProperty("WarningMsg_NonExistingProduct"),"Warrning message does not displayed");
	}
	

	@Test(priority = 3,enabled = true)
	public void verifySearchWithoutEntringProduct() throws InterruptedException {
		homepage=new HomePage(driver);
	
		search=homepage.SearchBtn_Click();
		Thread.sleep(3000);
		
		
		String ActualWarrningMessage=search.searchproduct_warningmsg();
		
		Assert.assertEquals(ActualWarrningMessage,dataProp.getProperty("WarningMsg_NonExistingProduct"),"no search product does not displayed");
	}
	
}
