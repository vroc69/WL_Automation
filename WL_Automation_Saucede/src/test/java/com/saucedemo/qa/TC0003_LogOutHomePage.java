package com.saucedemo.qa;

import org.testng.annotations.Test;

import com.saucedemo.base.GlobalVariables;
import com.saucedemo.base.SeleniumWrapper;
import com.saucedemo.common.LogIn;
import com.saucedemo.common.LogOut;

import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class TC0003_LogOutHomePage {
	WebDriver driver;
	SeleniumWrapper seleniumWrapper;
	LogIn login;
	LogOut logout;
	 
	String userN, psw;
  
	@BeforeTest
	public void beforeTest() throws FileNotFoundException {
		seleniumWrapper = new SeleniumWrapper(driver);
		driver = seleniumWrapper.chromeDriverConnection();
		login = new LogIn(driver);
		logout = new LogOut(driver);
		
		//Using Json
		userN = seleniumWrapper.getJSONValue("usersJ","standard","username");
		psw = seleniumWrapper.getJSONValue("usersJ","standard","password");
	}
	  
	@Test
	public void f() throws IOException {
		//step1 - open browser and go to app
		seleniumWrapper.launchBrowser(GlobalVariables.QA_URL);
						
		//step2 - Login
		login.loginSaucedemo(userN, psw, "TC003");
						
		//step3 - Validate success login.
		login.validateLogin("TC003");
		
		//step4 - Logout
	    logout.logoutSaucedemo();
	    
	    //step5 - Validate return to login page
	    login.validaLoginIsDisplayed("TC003");
	}
	  
	@AfterTest
	public void afterTest() {
		logout.closeAppSaucedemo();
	}

}//close class
