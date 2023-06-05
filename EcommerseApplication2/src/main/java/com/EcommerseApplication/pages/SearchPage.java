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
	
}
