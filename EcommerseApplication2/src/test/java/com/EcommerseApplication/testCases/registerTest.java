package com.EcommerseApplication.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.EcommerseApplication.BasePackage.BaseClass;
import com.EcommerseApplication.pages.AccountPage;
import com.EcommerseApplication.pages.AccountSucessPage;
import com.EcommerseApplication.pages.ForgottenPasswordPage;
import com.EcommerseApplication.pages.HomePage;
import com.EcommerseApplication.pages.LoginPage;
import com.EcommerseApplication.pages.RegisterPage;
import com.EcommerseApplication.utilis.Log;
import com.EcommerseApplication.utilis.utilities;

public class registerTest  extends BaseClass{

	public WebDriver driver;
	RegisterPage register;
	AccountSucessPage acSuccess;
	LoginPage loginobject;
	ForgottenPasswordPage forgotobject;
	AccountPage Myaccount;

	public registerTest()
	{
		super();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String broswername) {

		//driver=initilizeBrowser(prop.getProperty("browser"));
		driver=initilizeBrowser(broswername);

	}	


	@Test(priority = 1,enabled = true)
	public void VerifyRegisterAccountWithMandetoryField() {
		Log.startTestCase("VerifyRegisterAccountWithMandetoryField");
		HomePage homepage=new HomePage(driver);
		Log.info("Click on MyAcount Dropdown");
		homepage.clickOnMyAccount();
		register = homepage.RegisterOption_Click();
		Log.info("Click on Register Option link");


		Log.info("Entering Firstname");
		register.Set_Firstname(dataProp.getProperty("FirstName"));
		Log.info("Enter firstname Successfull");
		Log.info("Entering lastname");
		register.Set_Lastname(dataProp.getProperty("LastName"));
		Log.info("Entering Lastname Successfull");

		Log.info("Entering Email Address");
		register.Set_email(utilities.generateEmailWithTimeStamp());
		Log.info("Entering Email Address Successfully");

		Log.info("Entering telephone");
		register.Set_telephone(dataProp.getProperty("TelePhone"));
		Log.info("Entering telephone Successfully");
		Log.info("Entering Password");
		register.Set_password("270619");
		Log.info("Entering password Successfully");
		Log.info("Entering confirm password");
		register.Set_confirmpass("270619");
		Log.info("Entering confirm password Successfully");
		register.Click_Agree();
		Log.info("Click on Checkbox");
		Log.info("Click on continue");
		acSuccess = register.click_Continue();

		Log.info("Validation started");
		String ActualSuccessMessage =acSuccess.retriveTheAccountSucessHeading(); 
		String ExpectedSuccessMessage=dataProp.getProperty("AccountSucessMesg");
		Assert.assertEquals(ActualSuccessMessage,ExpectedSuccessMessage,"Account SuccessPage is not displayed");
		Log.info("validation Completed");
		Log.endTestCase("VerifyRegisterAccountWithMandetoryField");

	}
	@Test(priority = 2,enabled = true)
	public void VerifyRegisterAccountWithAllField() {
		Log.startTestCase("VerifyRegisterAccountWithAllField");
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		Log.info("Click on MyAcount Dropdown");
		register =homepage.RegisterOption_Click();
		Log.info("Click on Register Option link");

		Log.info("Entering Firstname");
		register.Set_Firstname(dataProp.getProperty("FirstName"));
		Log.info("Enter firstname Successfull");
		Log.info("Entering lastname");
		register.Set_Lastname(dataProp.getProperty("LastName"));
		Log.info("Entering Lastname Successfull");

		Log.info("Entering Email Address");
		register.Set_email(utilities.generateEmailWithTimeStamp());
		Log.info("Entering Email Address Successfully");

		Log.info("Entering telephone");
		register.Set_telephone(dataProp.getProperty("TelePhone"));
		Log.info("Entering telephone Successfully");

		Log.info("Entering Password");
		register.Set_password("270619");
		Log.info("Entering password Successfully");

		Log.info("Entering confirm password");
		register.Set_confirmpass("270619");
		Log.info("Entering confirm password Successfully");


		Log.info("Click on Newsletter checkobx");
		register.Click_newsletter();

		Log.info("Click on Checkbox");

		register.Click_Agree();
		Log.info("Click on continue");
		acSuccess=register.click_Continue();

		Log.info("Validation started");
		String ActualSuccessMessage =acSuccess.retriveTheAccountSucessHeading();

		String ExpectedSuccessMessage=dataProp.getProperty("AccountSucessMesg");
		Assert.assertEquals(ActualSuccessMessage,ExpectedSuccessMessage,"Account SuccessPage is not displayed");

		Log.info("validation Completed");
		Log.endTestCase("VerifyRegisterAccountWithAllField");


	}


	@Test(priority = 3,enabled = true)
	public void verifyRegisterPageWithExsitingEmail()
	{
		Log.startTestCase("verifyRegisterPageWithExsitingEmail");
		HomePage homepage=new HomePage(driver);
		Log.info("Click on MyAcount Dropdown");
		homepage.clickOnMyAccount();

		register=homepage.RegisterOption_Click();
		Log.info("Click on Register Option link");



		register.Set_Firstname(dataProp.getProperty("FirstName"));
		register.Set_Lastname(dataProp.getProperty("LastName"));
		register.Set_email(prop.getProperty("validUsername"));
		register.Set_telephone(dataProp.getProperty("TelePhone"));
		register.Set_password(prop.getProperty("ValidPassword"));
		register.Set_confirmpass(prop.getProperty("ValidPassword"));
		register.Click_newsletter();
		register.Click_Agree();
		register.click_Continue();
		String ActualWarnningMessage = register.Get_Warningmsgfrom_register_on_email();
		String ExpectedWarnningMessage=dataProp.getProperty("ExpectedWarnningMessage_ForExistingEmail");
		Assert.assertEquals(ActualWarnningMessage,ExpectedWarnningMessage,"Account SuccessPage is not displayed");
		Log.endTestCase("verifyRegisterPageWithExsitingEmail");

	}

	@Test(priority = 4,enabled = true)
	public void verifyRegisterPageWithoutProvidingfeild()
	{
		Log.startTestCase("verifyRegisterPageWithoutProvidingfeild");
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		register=homepage.RegisterOption_Click();

		register.click_Continue();

		String ActPrivacyPolicyWarMes=register.ActPrivacyPolicyWarMes_text();
		String ExcPrivacyPolicyWarMes=dataProp.getProperty("ExcPrivacyPolicyWarMes_tittle");
		Assert.assertEquals(ActPrivacyPolicyWarMes, ExcPrivacyPolicyWarMes,"Warnning message For Privacy policy not Display");

		String ActFirstNameWarMes=register.ActFirstNameWarMes_text();
		String ExcFirstNameWarMes=dataProp.getProperty("ExcFirstNameWarMes_tittle");
		Assert.assertEquals(ActFirstNameWarMes, ExcFirstNameWarMes,"Warnning message For First Name not Display");


		String ActLastNameWarMes=register.ActLastNameWarMes_text();
		String ExcLastNameWarMes=dataProp.getProperty("ExcLastNameWarMes_tittle");
		Assert.assertEquals(ActLastNameWarMes, ExcLastNameWarMes,"Warnning message For Last Name not Display");

		String ActEmailWarMes=register.ActEmailWarMes_text();
		String ExcEmailWarMes=dataProp.getProperty("ExcEmailWarMes_tittle");
		Assert.assertEquals(ActEmailWarMes, ExcEmailWarMes,"Warnning message For Email not Display");


		String ActTelephoneWarMes=register.ActTelephoneWarMess_text();
		String ExcTelephoneWarMes=dataProp.getProperty("ExcTelephoneWarMes_tittle");
		Assert.assertEquals(ActTelephoneWarMes, ExcTelephoneWarMes,"Warnning message For Telephone not Display");


		String ActPasswordWarMes=register.ActPasswordWarMes_text();
		String ExcPasswordWarMes=dataProp.getProperty("ExcPasswordWarMes_tittle");
		Assert.assertEquals(ActPasswordWarMes, ExcPasswordWarMes,"Warnning message For Password not Display");
		Log.endTestCase("verifyRegisterPageWithoutProvidingfeild");

	}

	@Test(priority = 5,enabled = true)
	public void Verify_Allfieldhave_proper_Placeholder()
	{
		Log.startTestCase("Verify_Allfieldhave_proper_Placeholder");
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		register=homepage.RegisterOption_Click();


		String Firstname_placeholder = register.Get_Firstnme_placeholername();
		String Lastname_placeholer=register.Get_lastname_placeholername();
		String email_placeholer=register.Get_Email_placeholername();
		String telephone_placeholder=register.Get_Telephone_placeholername();
		String password_placeholder=register.Get_Password_placeholername();
		String confirm_placeholer=register.Get_PasswordConfirm_placeholername();

		SoftAssert sa=new SoftAssert();
		sa.assertEquals(Firstname_placeholder,dataProp.getProperty("placeholder_firstname"),"Firstname placeholder is not shown proper");
		sa.assertEquals(Lastname_placeholer, dataProp.getProperty("placeholder_lastname"),"Lastname placeholder is not shown proper");
		sa.assertEquals(email_placeholer, dataProp.getProperty("placeholder_email"),"Email placeholder is not shown proper");
		sa.assertEquals(telephone_placeholder, dataProp.getProperty("placeholder_telephone"),"Telephone placeholder is not shown proer");
		sa.assertEquals(password_placeholder,dataProp.getProperty("placeholder_password"),"Password placeholder is not shown proper");
		sa.assertEquals(confirm_placeholer, dataProp.getProperty("placeholer_confirmpass"),"confirm password is not shown proper");

		sa.assertAll();

		Log.endTestCase("Verify_Allfieldhave_proper_Placeholder");

	}

	@Test(priority = 6,enabled = true)
	public void Test_PrivacyPolicyCheckbox_notselected_Bydefault()
	{
		Log.startTestCase("Test_PrivacyPolicyCheckbox_notselected_Bydefault");
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		register=homepage.RegisterOption_Click();


		boolean Default_privacyPolicy_Checkbox_value = register.Default_privacyPolicyCheckbox_click_check();

		Assert.assertEquals(Default_privacyPolicy_Checkbox_value,false,"privacy policy checked by default");
		Log.endTestCase("Test_PrivacyPolicyCheckbox_notselected_Bydefault");
	}

	@Test(priority = 7,enabled = true)
	public void Test_RegisterAccount_withoutSelcting_privacyPolicycheckbox() throws InterruptedException
	{
		Log.startTestCase("Test_RegisterAccount_withoutSelcting_privacyPolicycheckbox");
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		register=homepage.RegisterOption_Click();


		register.Set_Firstname(dataProp.getProperty("FirstName"));
		register.Set_Lastname(dataProp.getProperty("LastName"));
		register.Set_email(utilities.generateEmailWithTimeStamp());
		register.Set_telephone(dataProp.getProperty("TelePhone"));
		register.Set_password("270619");
		register.Set_confirmpass("270619");


		register.Click_newsletter();

		register.click_Continue();

		Thread.sleep(3000);

		String ActPrivacyPolicyWarMes=register.ActPrivacyPolicyWarMes_text();
		String ExcPrivacyPolicyWarMes=dataProp.getProperty("ExcPrivacyPolicyWarMes_tittle");
		Assert.assertEquals(ActPrivacyPolicyWarMes, ExcPrivacyPolicyWarMes,"Warnning message For Privacy policy not Display");

		Log.endTestCase("Test_RegisterAccount_withoutSelcting_privacyPolicycheckbox");
	}

	@Test(priority = 8,enabled = true)
	public void TC_Rf_025_VerifyBreadcrumb()
	{

		Log.startTestCase("TC_Rf_025_VerifyBreadcrumb");
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		register=homepage.RegisterOption_Click();


		String actulaurl=register.GetPageUrl();
		String Actulapagettitle = register.Gettittle();
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(actulaurl,dataProp.getProperty("Pageurl"),"Url is not show");
		sa.assertEquals(Actulapagettitle, dataProp.getProperty("Pagettitle"),"page tittle is not show");
		Log.endTestCase("TC_Rf_025_VerifyBreadcrumb");

	}

	@Test(priority = 9,enabled = true)
	public void TC_RF_023_VerificationOfOtherNavigationPageLink() throws InterruptedException
	{
		Log.startTestCase("TC_RF_023_VerificationOfOtherNavigationPageLink");
		SoftAssert sa=new SoftAssert();
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		register=homepage.RegisterOption_Click();

		System.out.println("click on register in myaccount dropdown");


		register=register.Resgisterpagelink_Click();
		sa.assertEquals(register.Registerpage_tiitle(),dataProp.getProperty("Registerpagettitle"),"Register page tittle is not shown correctly ");
		forgotobject=register.Forgotpasswordlink_click();
		sa.assertEquals(forgotobject.PageTittle_Forgetpassword(),dataProp.getProperty("ForgotPasswordPagetittle"),"Forgotten password page tittle is not shown correctly");
		driver.navigate().back();

		loginobject = register.loginlink_click();
		sa.assertEquals(loginobject.Pagetiitle_loginpage(),dataProp.getProperty("Loginpagettitle"),"Login page titile is not shown correctly");

		Myaccount = loginobject.LoginMethod(prop.getProperty("validUsername"),prop.getProperty("ValidPassword"));

		Thread.sleep(2000);

		sa.assertEquals(Myaccount.pagetittle_Myaccount(),dataProp.getProperty("MyAccountpagetittle"),"My account page tiitle is not shown correctly");

		Log.endTestCase("TC_RF_023_VerificationOfOtherNavigationPageLink");
	}

	@Test(priority = 10,enabled = true)
	public void TC_RF_008_VerifyregisterFucnalityDiffrentpassAndPassconfirm()
	{
		Log.startTestCase("TC_RF_008_VerifyregisterFucnalityDiffrentpassAndPassconfirm");
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		register=homepage.RegisterOption_Click();



		register.Set_Firstname(dataProp.getProperty("FirstName"));
		register.Set_Lastname(dataProp.getProperty("LastName"));
		register.Set_email(prop.getProperty("validUsername"));
		register.Set_telephone(dataProp.getProperty("TelePhone"));
		register.Set_password(prop.getProperty("ValidPassword"));
		register.Set_confirmpass(prop.getProperty("Invalidpassword"));
		register.Click_newsletter();
		register.Click_Agree();
		register.click_Continue();
		String ActualWarnningMessage = register.Actualpassconfirmmes();
		String ExpectedWarnningMessage=dataProp.getProperty("ErrormsgOFpasswordAndConfirmpass");
		Assert.assertEquals(ActualWarnningMessage,ExpectedWarnningMessage,"error msg of password and confirm password is not displayed");

		Log.endTestCase("TC_RF_008_VerifyregisterFucnalityDiffrentpassAndPassconfirm");
	}

	@Test(priority = 11,enabled = true)
	public void TC_RF_008_VerifyregisterFucnalityDiffrentpassAndPassconfirmisBlank() throws InterruptedException
	{
		Log.startTestCase("TC_RF_008_VerifyregisterFucnalityDiffrentpassAndPassconfirmisBlank");
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		register=homepage.RegisterOption_Click();



		register.Set_Firstname(dataProp.getProperty("FirstName"));
		register.Set_Lastname(dataProp.getProperty("LastName"));
		register.Set_email(prop.getProperty("validUsername"));
		register.Set_telephone(dataProp.getProperty("TelePhone"));
		register.Set_password(prop.getProperty("ValidPassword"));
		register.Set_confirmpass(" ");
		register.Click_newsletter();
		register.Click_Agree();
		register.click_Continue();
		Thread.sleep(3000);
		String ActualWarnningMessage = register.Actualpassconfirmmes();
		String ExpectedWarnningMessage=dataProp.getProperty("ErrormsgOFpasswordAndConfirmpass");
		Assert.assertEquals(ActualWarnningMessage,ExpectedWarnningMessage,"error msg of password and confirm password is not displayed");

		Log.endTestCase("TC_RF_008_VerifyregisterFucnalityDiffrentpassAndPassconfirmisBlank");
	}









}
