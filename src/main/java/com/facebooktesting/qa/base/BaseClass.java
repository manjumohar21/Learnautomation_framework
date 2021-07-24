package com.facebooktesting.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.facebooktesting.qa.util.TestUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {
	public static WebDriver driver;
	public static Properties property; // Making public So that we can use in all Child Classes.
	public static EventFiringWebDriver e_driver;
	public static Logger Log;
	public static ExtentReports extent;
	public static ExtentTest extentTest;

	// Using Base Class we achieving Inheritance Concept from Java.
	public BaseClass() // Constructor to read data from property file.
	{
		Log = Logger.getLogger(this.getClass()); // Logger Implementation.
		try {
			property = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/facebooktesting/qa/config/config.properties");
			property.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// Browser launching taking place here
	public static void initialization()

	{
		System.setProperty("webdriver.edge.driver", "E:\\msedgedriver.exe"); 
	//	System.setProperty("",""); Will give java.lang.IllegalArgumentException: key can't be empty
		driver = new EdgeDriver();	//	String browserName = property.getProperty("Browser");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtility.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		/*TestUtility.PAGE_LOAD_TIMEOUT
		 * Static variable is called by : Classname.variable name :*/
		driver.manage().timeouts().implicitlyWait(TestUtility.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(property.getProperty("URL"));
	}
}
