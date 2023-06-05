package com.EcommerseApplication.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ForgottenPasswordPage {
	
	WebDriver driver;

	public ForgottenPasswordPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String PageTittle_Forgetpassword()
	{
		return driver.getTitle();
	}
	
}
