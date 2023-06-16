/**
 * 
 */
package com.EcommerseApplication.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Tejal
 *
 */
public class LogOutPage {

	WebDriver driver;
	
	@FindBy(xpath = "//a[contains(text(),'Continue')]")
	private WebElement btn_continue;
	
	
	
	public LogOutPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public HomePage Click_on_Continue()
	{
		btn_continue.click();
		return new HomePage(driver);
	}
	public String Get_logoutPage_tittle()
	{
		return driver.getTitle();
	}
	
	
}
