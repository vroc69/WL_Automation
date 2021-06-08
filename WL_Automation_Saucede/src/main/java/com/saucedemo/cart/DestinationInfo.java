package com.saucedemo.cart;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.saucedemo.base.SeleniumWrapper;

public class DestinationInfo extends SeleniumWrapper
{

	public DestinationInfo(WebDriver driver) {
		super(driver);
	}
	
	By checkOut_txt = By.xpath("//span[@class='title' and contains(text(),'Checkout: Your Information')]");
	By firstName_input = By.xpath("//input[@id='first-name']");
	By lastName_input = By.xpath("//input[@id='last-name']");
	By zipCode_input = By.xpath("//input[@id='postal-code']");
	By cancel_btn = By.xpath("//button[@id='cancel']");
	By continue_btn = By.xpath("//input[@id='continue' and @type='submit']");
	
	/**
	 * Complete info
	 * @param name,lastname,zipCode,testcase
	 * @throws IOException 
	 */
	public void completeInfo(String name, String lastname, String zipCode, String testcase) throws IOException {
		reporterLog("Populating information...");
		waitForElementPresent(checkOut_txt);
		writeOn(firstName_input, name);
		implicitlyWait(10);
		writeOn(lastName_input, lastname);
		implicitlyWait(10);
		writeOn(zipCode_input, zipCode);
		implicitlyWait(10);
		takeScreenshot(testcase, "checkOutInfo");
		implicitlyWait(20);
		
		clickOn(continue_btn);
		implicitlyWait(20);
		
	}//close complete info
	
}//close class
