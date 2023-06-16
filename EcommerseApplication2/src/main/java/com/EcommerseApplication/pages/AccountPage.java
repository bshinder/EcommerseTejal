package com.EcommerseApplication.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;
	
	
	
	@FindBy(xpath = "//a[contains(text(),'Edit your account information')]")
	private WebElement editYourAccountInfoOption;
	
	@FindBy(name="search")
	private WebElement Serabox_AccountPage;
	
	@FindBy(xpath = "//header/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]")
	private WebElement SerachButton_OFAccountpage;
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	//Action
	
	public boolean getDisplayStatusOfEditYourAccountInfoOpetion() {
		boolean dispalyStatus=editYourAccountInfoOption.isDisplayed();
		return dispalyStatus;
	}
	
	public String pagetittle_Myaccount()
	{
		return driver.getTitle();
	}
	
	public void EnterProductname_In_SerachBox(String productname)
	{
		Serabox_AccountPage.sendKeys(productname);
		SerachButton_OFAccountpage.click();
		
		
	}
	
	
	
}
