package com.saucedemo.qa;

import org.testng.annotations.Test;

import com.saucedemo.base.GlobalVariables;
import com.saucedemo.base.SeleniumWrapper;
import com.saucedemo.common.LogIn;
import com.saucedemo.common.LogOut;
import com.saucedemo.products.ProductsCatalog;

import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class TC004_SortProductsByPrice {
	WebDriver driver;
	SeleniumWrapper seleniumWrapper;
	LogIn login;
	LogOut logout;
	ProductsCatalog products;
	 
	String userN, psw;
	
	@BeforeTest
	public void beforeTest() {
		seleniumWrapper = new SeleniumWrapper(driver);
		driver = seleniumWrapper.chromeDriverConnection();
		login = new LogIn(driver);
		logout = new LogOut(driver);
		products = new ProductsCatalog(driver);
		
		//Using Excel
		userN = seleniumWrapper.getCellData("users", 1, 0);
		psw = seleniumWrapper.getCellData("users", 1, 1);
	}
	
	@Test
	public void f() throws IOException {
		//step1 - open browser and go to app
		seleniumWrapper.launchBrowser(GlobalVariables.QA_URL);
				
		//step2 - Login
		login.loginSaucedemo(userN, psw, "TC004");
		login.validateLogin("TC004");
				
		//step3 - Sort products by price and validate it.
		products.sortProducts("lohi", "TC004");
		products.getProductAttr("TC004", "Prices");
		
		//step4 - Logout
		logout.logoutSaucedemo();
	}
	
	@AfterTest
	public void afterTest() {
		logout.closeAppSaucedemo();
	}

}//close class
