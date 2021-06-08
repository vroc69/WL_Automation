package com.saucedemo.cart;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.saucedemo.base.SeleniumWrapper;

public class PurchaseDone extends SeleniumWrapper
{

	public PurchaseDone(WebDriver driver) {
		super(driver);
	}
	
	By completeP_txt = By.xpath("//span[@class='title']");
	By completeP_container = By.xpath("//div[@id='checkout_complete_container']/h2");
	By bachHome_btn = By.xpath("//button[@id='back-to-products']");
	
	/**
	 * Validate purchase had finished
	 * @author VROC
	 * @param testcase
	 * @throws IOException 
	 */
	public void validatePurchaseFinished(String testcase) throws IOException {
		waitForElementPresent(completeP_txt);
		implicitlyWait(25);
		hardAssertion("THANK YOU FOR YOUR ORDER", getTextOf(completeP_container));
		takeScreenshot(testcase, "purchaseDone");
		clickOn(bachHome_btn);
		implicitlyWait(25);
		
	}//close validate finished
	
}//close class
