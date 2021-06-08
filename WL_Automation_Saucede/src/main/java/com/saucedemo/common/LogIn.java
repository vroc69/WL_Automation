package com.saucedemo.common;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import org.openqa.selenium.By;
import com.saucedemo.base.SeleniumWrapper;

public class LogIn extends SeleniumWrapper 
{
	public LogIn(WebDriver driver) {
		super(driver);
		
	}//close constructor
	
	//Repository of elemets
	By usrName_input = By.xpath("//input[@id='user-name']");
	By pswText_input = By.xpath("//input[@id='password']");
	By login_btn = By.xpath("//input[@id='login-button' and @type='submit']");
	By cart_link = By.xpath("//a[@class='shopping_cart_link']");
	By errorMsg_cointainer = By.xpath("//div[@class='error-message-container error']");
	By errorMsg_txt = By.xpath("//h3[@data-test='error']");
	By closeError_btn = By.xpath("//button[@class='error-button']");
	
	/**
	 * Login to app
	 * @autor VROC
	 * @param usr,psw,testcase 
	 * @throws IOException 
	 */
	public void loginSaucedemo(String usr, String psw, String testcase) throws IOException
	{
		reporterLog("Login to Saucedemo !!");
		waitForElementPresent(usrName_input);
		writeOn(usrName_input,usr);
		writeOn(pswText_input,psw);
		takeScreenshot(testcase,"beforeLogin");
		clickOn(login_btn);
		implicitlyWait(15);
		takeScreenshot(testcase,"afterLogin");
		implicitlyWait(8);
		
	}//close login
	
	/**
	 * Validate success login
	 * @throws IOException 
	 * @autor VROC
	 * @param testcase 
	 */
	public void validateLogin(String testcase) throws IOException {
		implicitlyWait(15);
		elementExist(cart_link);
		reporterLog("Success Login !!");
		takeScreenshot(testcase,"successLogin");
		
	}//close validate login
	
	/**
	 * Validate fail login
	 * @throws IOException 
	 * @autor VROC
	 * @param testcase 
	 */
	public void validateFailLogin(String testcase) throws IOException {
		elementExist(errorMsg_cointainer);
		reporterLog("Invalid User !!");
		String errorM = getTextOf(errorMsg_txt);		
		hardAssertion("Epic sadface: Sorry, this user has been locked out.", errorM);
		takeScreenshot(testcase,"Not able to access !!");
		implicitlyWait(10);
		clickOn(closeError_btn);
				
	}//close validate fail login
	
	/**
	 * Validate login the user is placed on login page
	 * @throws IOException 
	 * @autor VROC
	 * @param testcase 
	 */
	public void validaLoginIsDisplayed(String testcase) throws IOException {
		elementExist(usrName_input);
		elementExist(pswText_input);
		waitForElementPresent(login_btn);
		reporterLog("Login page is displayed !!");
		implicitlyWait(15);
		takeScreenshot(testcase,"returnToLogin");
	}
	
}//close class
