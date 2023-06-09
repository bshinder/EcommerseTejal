package com.EcommerseApplication.BasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeSuite;

import com.EcommerseApplication.utilis.utilities;

import io.github.bonigarcia.wdm.managers.OperaDriverManager;

public class BaseClass {
  
    WebDriver driver;
	public Properties prop;
    public Properties dataProp;
	
	public  BaseClass()  {
		
		
		
		 prop= new Properties();
         dataProp=new Properties();
		File PropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\EcommerseApplication\\config\\config.properties");
		File DataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\EcommerseApplication\\TestData\\TestData.properties");
		try {
			FileInputStream fis = new FileInputStream(PropFile);
			prop.load(fis);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
		FileInputStream fis2;
		try {
			fis2 = new FileInputStream(DataPropFile);
			dataProp.load(fis2);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		

	}
	
	@BeforeSuite
	public void beforesuite()
	{
		DOMConfigurator.configure("log4j.xml");
	}
	
	
	
	public WebDriver initilizeBrowser(String browserName) {

		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("safari"))
		{
			driver=new SafariDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			driver=new InternetExplorerDriver();
		}
		else if(browserName.equalsIgnoreCase("opera"))
		{
			driver=(WebDriver) new OperaDriverManager();
		}
				
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utilities.ImplicitWaitTime));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(utilities.PageLoadTime));
			driver.get(prop.getProperty("url"));
			
			return driver;
	
	}
	
}
