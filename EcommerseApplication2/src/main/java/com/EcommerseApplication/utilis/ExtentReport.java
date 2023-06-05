package com.EcommerseApplication.utilis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static ExtentReports genrateExtentReport() throws IOException
	{
		ExtentReports reports=new ExtentReports();
		
		File extentreportfile=new File(System.getProperty("user.dir")+"\\test-output\\extentreports\\extentreport.html" );
		ExtentSparkReporter sparkreport=new ExtentSparkReporter(extentreportfile);
		sparkreport.config().setTheme(Theme.DARK);
		sparkreport.config().setReportName("EcommerseApplication Report");
		sparkreport.config().setDocumentTitle("Automation result");
		sparkreport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		reports.attachReporter(sparkreport);
		
		Properties configprop=new Properties();
		File configpropfile=new File( System.getProperty("user.dir")+"\\src\\main\\java\\com\\EcommerseApplication\\config\\config.properties");
		FileInputStream fis=new FileInputStream(configpropfile);
		configprop.load(fis);
		
		reports.setSystemInfo("Application URL", configprop.getProperty("url"));
		reports.setSystemInfo("BrowserName",configprop.getProperty("browser"));
		reports.setSystemInfo("Username OF Application",configprop.getProperty("validUsername"));
		reports.setSystemInfo("Application Password",configprop.getProperty("ValidPassword"));
		reports.setSystemInfo("Operating System",System.getProperty("os.name") );
		reports.setSystemInfo("Username",System.getProperty("user.name"));
		reports.setSystemInfo("Java Version",System.getProperty("java.version"));
		reports.setSystemInfo("QA","Tejal");
		
		return reports;
		
	}
}
