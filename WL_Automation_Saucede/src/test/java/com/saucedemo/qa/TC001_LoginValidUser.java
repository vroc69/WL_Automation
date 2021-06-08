package com.saucedemo.qa;

import org.testng.annotations.Test;

import com.saucedemo.base.GlobalVariables;
import com.saucedemo.base.SeleniumWrapper;
import com.saucedemo.common.LogIn;
import com.saucedemo.common.LogOut;

import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class TC001_LoginValidUser 
{	
	WebDriver driver;
	SeleniumWrapper seleniumWrapper;
	LogIn login;
	LogOut logout;
	
	String userN, psw;
	
	@BeforeTest
	public void beforeTest() {
		seleniumWrapper = new SeleniumWrapper(driver);
		driver = seleniumWrapper.chromeDriverConnection();
		login = new LogIn(driver);
		logout = new LogOut(driver);
		
		//Using Excel
		userN = seleniumWrapper.getCellData("users", 1, 0);
		psw = seleniumWrapper.getCellData("users", 1, 1);
	}
	
	@Test
	public void f() throws IOException {
		//step1 - open browser and go to app
		seleniumWrapper.launchBrowser(GlobalVariables.QA_URL);
		
		//step2 - Login
		login.loginSaucedemo(userN, psw, "TC001");
		
		//step3 - Validate the user navigates to the products page when logged in.
	    login.validateLogin("TC001");
		
		//step4 - Logout
	    logout.logoutSaucedemo();
	    
	}
	
	@AfterTest
	public void afterTest() {
		logout.closeAppSaucedemo();
	}
	
}//close class
