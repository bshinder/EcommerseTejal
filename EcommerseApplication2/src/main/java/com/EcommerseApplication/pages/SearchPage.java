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
public class SearchPage {

	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement ProductLinkText;
	
	@FindBy(xpath="/html[1]/body[1]/div[2]/div[1]/div[1]/p[2]")
	private WebElement Warningmsg_Serachproduct;
	
	@FindBy(id="input-search")
	private WebElement Searchcriteria_textbox;
	
	@FindBy(name="search")
	private WebElement txt_serachTextbox;
	
	@FindBy(id="button-search")
	private WebElement btn_ButtonSerach;
	
	
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean Search_Productname()
	{
		return ProductLinkText.isDisplayed();
	}
	
	public String searchproduct_warningmsg()
	{
		String Warningmsg_Serachproduct_text = Warningmsg_Serachproduct.getText();
		return Warningmsg_Serachproduct_text;
	}
	
	public String GetSaerchTextbox_Placeholder()
	{
		String txtserache_placeholdertext = txt_serachTextbox.getAttribute("Search");
		return txtserache_placeholdertext;
		
	}
	public String GetSearchCriteriaTextbox_placeholder()
	{
		String txtSerachCriteria_placeholdertext = Searchcriteria_textbox.getAttribute("Keywords");
		return txtSerachCriteria_placeholdertext;
	}
	
	public boolean SearchCriteria_textbox_visibility()
	{
	
		boolean result = Searchcriteria_textbox.isDisplayed();
		return result;
	}
	
	public void SaerchCriteria_Text_ValueInsert(String value)
	{
		Searchcriteria_textbox.sendKeys(value);
	}
	
	public void click_Btnsearch()
	{
		btn_ButtonSerach.click();
	}
	
}
