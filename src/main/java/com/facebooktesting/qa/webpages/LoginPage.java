package com.facebooktesting.qa.webpages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.facebooktesting.qa.base.BaseClass;
import com.facebooktesting.qa.util.TestUtility;

public class LoginPage extends BaseClass { // Concept of Inheritance
	
	/**Initialising the Page Object*/
	public LoginPage() { //Create Constructor of Login Class
		PageFactory.initElements(driver, this);//THis means current class
		
		/*Page Factory will initialize every WebElement variable with a reference 
		 * to a corresponding element on the actual web page based on “locators” defined.
		 *  This is done by using @FindBy annotations.*/
	}

	TestUtility test = new TestUtility();
	
	@FindBy(name= "email")
	WebElement username;/*All method calls will do a freshness check to ensure that the element reference is still valid.
	This essentially determines whether or not the element is still attached to the DOM. 
	If this testfails, then an StaleElementReferenceException is thrown, 
	and all future calls to this instance will fail. */

	 
	@FindBy(name = "pass")
	WebElement password;
	
	@FindBy(name = "login")
	WebElement loginbtn;
	
	
	//Actions or Features
	public void login_fill_data(String username1, String password1) {
	System.out.println(username1);
	System.out.println(password1);
	TestUtility.sendKeys(driver,username,TestUtility.EXPLICIT_WAIT,username1); //Classname.function();
	TestUtility.sendKeys(driver,password,TestUtility.EXPLICIT_WAIT,password1);
		
	}
	
	public void click_to_login()
	{
		TestUtility.OnClickLogin(driver, loginbtn, TestUtility.EXPLICIT_WAIT);
	}
	
	public void Screenshot() throws IOException {
		String screenshot = "Facebook_login_page";
		TestUtility.getScreenshot(driver, screenshot);
	}
	
}
