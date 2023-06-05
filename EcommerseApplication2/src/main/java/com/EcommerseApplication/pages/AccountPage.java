package com.EcommerseApplication.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;
	
	
	
	@FindBy(xpath = "//a[contains(text(),'Edit your account information')]")
	private WebElement editYourAccountInfoOption;
	
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
}
