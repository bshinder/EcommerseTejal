package com.EcommerseApplication.Listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.EcommerseApplication.utilis.ExtentReport;
import com.EcommerseApplication.utilis.utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListners implements ITestListener{
	ExtentReports extentreport;
	ExtentTest extenttest;
	String Testname;
	
	@Override
	public void onStart(ITestContext context) {
		
		try {
			 extentreport = ExtentReport.genrateExtentReport();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}

	@Override
	public void onTestStart(ITestResult result) {
		Testname=result.getName();
		
	    extenttest = extentreport.createTest(Testname);
		extenttest.log(Status.INFO, Testname + " Start executing");
	
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extenttest.log(Status.PASS,Testname + "Test is pass");
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		 
		
		WebDriver driver=null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		String destinationScreenshotpath = utilities.Takescreenshotmethod(driver,Testname);
		
		extenttest.addScreenCaptureFromPath(destinationScreenshotpath);
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL, Testname + " got failed");
		
		System.out.println(Testname + "failed");
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extenttest.log(Status.INFO,result.getThrowable());
		extenttest.log(Status.SKIP, Testname + " got Skipped");
		
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		
		extentreport.flush();
		String pathofextentrport=System.getProperty("user.dir")+"\\test-output\\extentreports\\extentreport.html";
		File extentreport=new File(pathofextentrport);
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	

}
