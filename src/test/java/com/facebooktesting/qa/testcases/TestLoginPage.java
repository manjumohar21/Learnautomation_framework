package com.facebooktesting.qa.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.facebooktesting.qa.base.BaseClass;
import com.facebooktesting.qa.util.TestUtility;
import com.facebooktesting.qa.webpages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class TestLoginPage extends BaseClass{
	public static WebDriver driver;
	Logger log = Logger.getLogger("TestLoginPage");
	LoginPage page;
	public TestLoginPage(){
		super();
	}	
	
	@DataProvider(name = "Testdata")
	public Object[][] getTestData() throws IOException {
		String sheetNameFromProp = property.getProperty("testDatasheet1");
		Object data[][] = TestUtility.getTestData(sheetNameFromProp);

		return data;
	}

	
	@BeforeMethod
	
	public void setup()
	{
		Log.info("----------------------------------------");
		Log.info("-----------Launching Browser------------");
		Log.info("----------------------------------------");
		initialization();
		Log.info("------- Application Launched Successfully ---------- ");	
	   page = new LoginPage();

	}
	
@Test(priority=1)
	
	public void verifytitle()	{
	System.out.println("Hello");	
	}

	@Test(priority = 1, dataProvider = "Testdata")
	public void verifyingLoginPage(String Username2, String Password2) throws IOException, InterruptedException {
		Log.info("-------------TEST CASE STARTED---------------------------");
		Log.info("Filling Data");
		page.login_fill_data(Username2, Password2);
		page.click_to_login();
        Thread.sleep(10000);
		page.Screenshot();
		Log.info("Clicking on Checkout button");
		
	}
	
	@AfterMethod
	
	public void tearDown() { 
		Log.info("-----------------------------------------------");
		Log.info("-------- Browser Terminated --------------------");
		Log.info("-----------------------------------------------");
	    //driver.quit();
		
		if(driver!=null)
		{
		driver.quit();
		}
		
		else
		{
			System.out.println("hello");
		}
	}
		
	}

