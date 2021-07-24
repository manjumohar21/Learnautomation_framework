package com.facebooktesting.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.facebooktesting.qa.base.BaseClass;

public class TestUtility extends BaseClass {

	/*Everything*/
	// 1. These 2 variable we used in TestBase Class for Page Load and Implicit
	// Wait.
	public static long PAGE_LOAD_TIMEOUT = 50;
	public static long IMPLICIT_WAIT = 50;
	public static long EXPLICIT_WAIT = 25;
	public static long Happy;

	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "/src/main/java/com/facebooktesting/qa/testdata/Testdata.xlsx";

	static Workbook book;
	static Sheet sheet;
	
	// Making a 2D array to store the excel data (using in data provider)
	public static Object[][] getTestData(String sheetName) throws IOException {

		//System.out.println("FilePath  " + TESTDATA_SHEET_PATH);
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		book = new XSSFWorkbook(file);
		System.out.println("sheetname:  "+sheetName);
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println("excel in utility "+data[i][k]);

			}

		}

		return data;
	}
	// Explicit Wait for Sending Data to any Element.
		public static void sendKeys(WebDriver driver, WebElement element, long timeout, String value) {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(value);
		}
	
		public static void OnClickLogin(WebDriver driver, WebElement element,long timeout)
		{
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
			element.click();

			
		}

		// Method to capturing screenshots and storing it to a dedicated location
		public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
			// We have generated Date now.
			String dateName = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver; // Creating instance of File
			/*To take a screenshot in Selenium, we use an interface called TakesScreenshot,
			 *  which enables the Selenium WebDriver to capture a screenshotand store it in different ways.
			 *   It has a got a method “ getScreenshotAs () ”
			 *    which captures the screenshot and store it in the specified location.
			 * */
			
			File source = ts.getScreenshotAs(OutputType.FILE);
			// After execution, you could see a folder "FailedTestsScreenshots"
			// Under Source folder
			String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
					+ ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);/*Copy screenshot to a location using CopyFile method*/
			return destination;
		}

		
}

