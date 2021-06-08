package com.saucedemo.qa;

import org.testng.annotations.Test;

import com.saucedemo.base.GlobalVariables;
import com.saucedemo.base.SeleniumWrapper;
import com.saucedemo.common.LogIn;
import com.saucedemo.common.LogOut;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TC002_LoginInvalidUser {
	WebDriver driver;
	SeleniumWrapper seleniumWrapper;
	LogIn login;
	LogOut logout;
	 
	String userN, psw;
  
	@BeforeTest
	public void beforeClass() throws FileNotFoundException {
		seleniumWrapper = new SeleniumWrapper(driver);
		driver = seleniumWrapper.chromeDriverConnection();
		login = new LogIn(driver);
		logout = new LogOut(driver);
		
		//Using Json
		userN = seleniumWrapper.getJSONValue("usersJ","locked_out","username");
		psw = seleniumWrapper.getJSONValue("usersJ","locked_out","password");
	}
	
	@Test
	public void f() throws IOException {
		//step1 - open browser and go to app
		seleniumWrapper.launchBrowser(GlobalVariables.QA_URL);
				
		//step2 - Login
		login.loginSaucedemo(userN, psw, "TC002");
				
		//step3 - Validate use and invalid user.
		login.validateFailLogin("TC002");
						
	}
  
	@AfterTest
	public void afterClass() {
		logout.closeAppSaucedemo();
	}

}//close class
