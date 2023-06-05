package com.EcommerseApplication.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	
	WebDriver driver;
	
	@FindBy(xpath="//span[contains(text(),'My Account')]")
	private WebElement MyAccountDropMenu;
	
	@FindBy(xpath="//a[contains(text(),'Login')]")
	private WebElement LoginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registeroption;
	
	@FindBy(name="search")
	private WebElement Searchox;
	
	@FindBy(xpath = "//header/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]/i[1]")
	private WebElement Search_Button;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void clickOnMyAccount() {
		MyAccountDropMenu.click();
		
	}
	
	public LoginPage SelectLoginOpetion() {
		LoginOption.click();
		return new LoginPage(driver);
	}
	public RegisterPage RegisterOption_Click()
	{
		registeroption.click();
		return new RegisterPage(driver);
		
	}
	
	public void SerachBox_Value(String productname)
	{
		Searchox.sendKeys(productname);
	}
	
	public SearchPage SearchBtn_Click()
	{
		Search_Button.click();
		return new SearchPage(driver);
	}
	
}
