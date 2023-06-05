package com.EcommerseApplication.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSucessPage {

	WebDriver driver;
	@FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created!')]")
	private WebElement AccountSucessheading;
	
	public AccountSucessPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String retriveTheAccountSucessHeading()
	{
		String AccountSuccefullycreated = AccountSucessheading.getText();
		return AccountSuccefullycreated;
	}
}
