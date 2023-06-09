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
public class RegisterPage {
	
  WebDriver driver;

	@FindBy(id="input-firstname")
	private WebElement firstname;
	
	@FindBy(id="input-lastname")
	private WebElement lastname;
	
	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(id="input-telephone")
	private WebElement telephone;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(id="input-confirm")
	private WebElement confirm_pass;
	
	@FindBy(name="agree")
	private WebElement chk_agree;
	
	@FindBy (xpath = "//*[@value='Continue']")
	private WebElement click_continue; 
	
	@FindBy(xpath = "//*[@name='newsletter' and @value='1']")
	private WebElement Newsletter;
	
	@FindBy(xpath = "//*[@name='newsletter' and @value='0']")
	private WebElement Newsletter_No;
	
	
	
	@FindBy(xpath = "//body/div[@id='account-register']/div[1]")
	private WebElement Register_warningmsg;
	
	@FindBy(xpath = "//body/div[@id='account-register']/div[1]")
	private WebElement ActPrivacyPolicyWarMes;
	
	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement ActFirstNameWarMes;
	
	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement ActLastNameWarMes;
	
	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement ActEmailWarMes;
	
	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement ActTelephoneWarMes;
	
	@FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement ActPasswordWarMes;
	
	@FindBy(xpath = "//div[contains(text(),'Password confirmation does not match password!')]")
	private WebElement PasswordAndConfirmpassMes;
	
	@FindBy(linkText = "Login")
	private WebElement Registerpagelink_login;
	
	@FindBy(linkText = "Register")
	private WebElement Registerpagelink_register;
	
	@FindBy(linkText = "Forgotten Password")
	private WebElement Registerpagelink_forgotpassword;
	
	@FindBy(linkText = "My Account")
	private WebElement Registerpagelink_Myaccount;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void Set_Firstname(String f_name)
	{
		firstname.sendKeys(f_name);		
	}
	
	public void Set_Lastname(String l_name)
	{
		lastname.sendKeys(l_name);
	}
	
	public void Set_email(String mail)
	{
		email.sendKeys(mail);
		
	}
	public void Set_telephone(String number)
	{
		telephone.sendKeys(number);
	}
	
	public void Set_password(String pass)
	{
		password.sendKeys(pass);
		
	}
	
	public void Set_confirmpass(String cpass) {
		confirm_pass.sendKeys(cpass);
	}
	
	public void Click_Agree() 
	{
		chk_agree.click();
		
	}
	
	public AccountSucessPage click_Continue()
	{
		click_continue.click();
		return new AccountSucessPage(driver);
		
	}
	public void Click_newsletter()
	{
		Newsletter.click();
	}
	public String Get_Warningmsgfrom_register_on_email()
	{
		String Emailwarningmsg = Register_warningmsg.getText();
		return Emailwarningmsg;
	}
	
	public String ActPrivacyPolicyWarMes_text()
	{
		String ActPrivacyPolicyWarMes_tittle = ActPrivacyPolicyWarMes.getText();
		return ActPrivacyPolicyWarMes_tittle;
	}
	
	public String ActFirstNameWarMes_text()
	{
		String ActFirstNameWarMes_tittle = ActFirstNameWarMes.getText();
		return ActFirstNameWarMes_tittle;
	}
	
	public String ActLastNameWarMes_text()
	{
		String ActLastNameWarMes_tittle = ActLastNameWarMes.getText();
		return ActLastNameWarMes_tittle;
	}
	
	public String ActEmailWarMes_text()
	{
		String ActEmailWarMes_tittle = ActEmailWarMes.getText();
		return ActEmailWarMes_tittle;
	}
	
	public String ActTelephoneWarMess_text()
	{
		String ActTelephoneWarMes_tittle = ActTelephoneWarMes.getText();
		return ActTelephoneWarMes_tittle;
	}
	
	public String ActPasswordWarMes_text()
	{
		String ActPasswordWarMes_tittle = ActPasswordWarMes.getText();
		return ActPasswordWarMes_tittle;
	}
	
	public void click_NewsletterNo()
	{
		Newsletter_No.click();
	}
	
	public String Get_Firstnme_placeholername()
	{
		String FirstnamePlaceholer_text = firstname.getAttribute("placeholder");
		return FirstnamePlaceholer_text;
	}
	
	public String Get_lastname_placeholername()
	{
		String LastnamePlaceholer_text = lastname.getAttribute("placeholder");
		return LastnamePlaceholer_text;
	}
	
	public String Get_Email_placeholername()
	{
		String EmailPlaceholer_text = email.getAttribute("placeholder");
		return EmailPlaceholer_text;
	}
	
	public String Get_Telephone_placeholername()
	{
		String telephonePlaceholer_text = telephone.getAttribute("placeholder");
		return telephonePlaceholer_text;
	}
	
	public String Get_Password_placeholername()
	{
		String PasswordPlaceholer_text = password.getAttribute("placeholder");
		return PasswordPlaceholer_text;
	}
	
	public String Get_PasswordConfirm_placeholername()
	{
		String confirmpassPlaceholer_text = confirm_pass.getAttribute("placeholder");
		return confirmpassPlaceholer_text;
	}
	
	public boolean Default_privacyPolicyCheckbox_click_check()
	{
		boolean checkvalue = chk_agree.isSelected();
		return checkvalue;
	}
	
	public String Gettittle()
	{
		String pagetittle=driver.getTitle();
		return pagetittle;
	}
	public String GetPageUrl()
	{
		String pageurl = driver.getCurrentUrl();
		return pageurl;
	}
	
	public LoginPage loginlink_click()
	{
		Registerpagelink_login.click();
		return new LoginPage(driver);
	}
	public RegisterPage Resgisterpagelink_Click()
	{
		Registerpagelink_register.click();
		return new RegisterPage(driver);
	}
	
	public String Registerpage_tiitle()
	{
		 return driver.getTitle();
	}
	public ForgottenPasswordPage Forgotpasswordlink_click()
	{
		Registerpagelink_forgotpassword.click();
		return new ForgottenPasswordPage(driver);
	}
	public LoginPage MyaccountPagelink_Click()
	{
		Registerpagelink_Myaccount.click();
		return new LoginPage(driver);
		
	}
	public String Actualpassconfirmmes()
	{
		String PasswordAndConfirmpassMes1=PasswordAndConfirmpassMes.getText();
		return PasswordAndConfirmpassMes1;
	}
}

