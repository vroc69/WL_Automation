package com.saucedemo.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.saucedemo.base.SeleniumWrapper;

public class LogOut extends SeleniumWrapper
{
	public LogOut(WebDriver driver) {
		super(driver);
		
	}//close constructor
	
	By menu_btn = By.xpath("//button[@id='react-burger-menu-btn']");
	By logOut_link = By.xpath("//a[@id='logout_sidebar_link']");
	
	/**
	 * LogOut saucedemo
	 * @author VROC
	 */
	public void logoutSaucedemo() {
		reporterLog("LogOut of app");
		clickOn(menu_btn);
		implicitlyWait(3);
		clickOn(logOut_link);
		implicitlyWait(3);
				
	}//close logOut
	
	/**
	 * Close app saucedemo
	 * @author VROC
	 */
	public void closeAppSaucedemo() {
		reporterLog("Closing browser window");
		closeBrowser();
	}//
	
}//close class
