package com.saucedemo.cart;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.saucedemo.base.SeleniumWrapper;

public class DetailsPurchase extends SeleniumWrapper 
{

	public DetailsPurchase(WebDriver driver) {
		super(driver);
	}
	
	By checkOverview_txt = By.xpath("//span[@class='title' and contains(text(),'Checkout: Overview')]");
	By cancel_btn = By.xpath("//button[@id='cancel']");
	By finish_btn = By.xpath("//button[@id='finish']");
	
	/**
	 * Finishing purchase
	 * @author VROC
	 * @throws IOException 
	 * 
	 */
	public void finishingPurchase(String testcase) throws IOException {
		waitForElementPresent(checkOverview_txt);
		implicitlyWait(25);
		takeScreenshot(testcase, "finishingPurchase");
		clickOn(finish_btn);
		implicitlyWait(25);
		
	}//close finishing purchase
	
}//close class
