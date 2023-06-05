package com.EcommerseApplication.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	@FindBy(name="email")
	private WebElement EmailField;
	
	@FindBy(name="password")
	private WebElement PasswordField;
	
	@FindBy(xpath = "//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")
	private WebElement SubmitBtn;
	
	@FindBy(xpath = "//body/div[@id='account-login']/div[1]")
	private WebElement EmailPasswordTextNotMatchingWarnning;
	
	@FindBy(linkText = "Forgotten Password")
	private WebElement ForgottenpasswordLink;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void EnterEmailAddress(String EmailText) {
		EmailField.sendKeys(EmailText);
	}
	
	public void EnterPassword(String PasswordText) {
		PasswordField.sendKeys(PasswordText );
	}
	
	public AccountPage ClickOnLogin() {
		SubmitBtn.click();
		return new AccountPage(driver);
	}
	
	public String RetriveWarnningEmailPassworNotMatchingText() {
		String EmailPasswordNotMachingWarnning = EmailPasswordTextNotMatchingWarnning.getText();
		return EmailPasswordNotMachingWarnning;
	}
	
	public AccountPage LoginMethod(String EmailText,String PasswordText )
	{
		EmailField.sendKeys(EmailText);
		PasswordField.sendKeys(PasswordText );
		
		SubmitBtn.click();
		return new AccountPage(driver);

		
		
	}
	
	public ForgottenPasswordPage Click_Forgottenpassword()
	{
		ForgottenpasswordLink.click();
		return new ForgottenPasswordPage();
	}
	
	public boolean ForgottenPassword_visible()
	{
		boolean ForgotpaswordLink = ForgottenpasswordLink.isDisplayed();
		return ForgotpaswordLink;
	}
	
	public String Get_placeholdername_username()
	{
		String email_placeholder = EmailField.getAttribute("placeholder");
		return email_placeholder;
	}
	
	public String get_placeholder_password()
	{
		String pass_placeholder = PasswordField.getAttribute("placeholder");
		return pass_placeholder;
	}
}
