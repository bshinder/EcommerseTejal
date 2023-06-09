package com.EcommerseApplication.utilis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class utilities {

	public static final int  ImplicitWaitTime=10;
	public static final int PageLoadTime=15;
	
	 public static String generateEmailWithTimeStamp() {
			Date date=new Date();
			String timeStamp= date.toString().replace(" ", "_").replace(":", "_");
			return "Tejal"+timeStamp+"@gmail.com";
		
		}
	 
	 public static Object[][] getTestDataFromExcel(String SheetName) {
		 
		 XSSFWorkbook workbook = null;
		 XSSFSheet sheet;
		 try {
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\EcommerseApplication\\TestData\\Testdata.xlsx");
			 workbook=new XSSFWorkbook(fis);
		 } catch ( Throwable e) {
			e.printStackTrace();
		}
		 
		 //sheet = workbook.getSheet("loginsheet");
		 sheet=workbook.getSheet(SheetName);
		 int rows= sheet.getLastRowNum();
		 System.out.println(rows);
		 int columns=sheet.getRow(0).getLastCellNum();
		 System.out.println(columns);
		 Object [][] Data= new Object[rows][columns];
		 for (int i=0;i<rows;i++)
		 {
			 XSSFRow row=sheet.getRow(i+1);
			 for(int j=0;j<columns;j++)
			 {
				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();
				switch(celltype)
				{
		
				case STRING:
					        Data[i][j]=cell.getStringCellValue();
					        break;
				case NUMERIC:
					        Data[i][j]=Integer.toString((int) cell.getNumericCellValue());
					        break;
				case BOOLEAN:
					         Data[i][j]=cell.getBooleanCellValue();
					         break;
					        }
			 }
			 
		 }
		 System.out.println(Data);
		 return Data;
		 
	 }
	 
	 public static String Takescreenshotmethod(WebDriver driver,String Testname )
	 {
		 File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destinationScreenshotpath=System.getProperty("user.dir")+"\\Screenshots\\"+Testname+".png";
			try {
				FileHandler.copy(srcScreenshot,new File(destinationScreenshotpath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return destinationScreenshotpath;
	 }
	 
	 
}
