package com.saucedemo.qa;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.saucedemo.base.GlobalVariables;
import com.saucedemo.base.SeleniumWrapper;
import com.saucedemo.cart.CartDetails;
import com.saucedemo.common.LogIn;
import com.saucedemo.common.LogOut;
import com.saucedemo.products.ProductsCatalog;

public class TC005_AddMultipleItemsToCart {
	WebDriver driver;
	SeleniumWrapper seleniumWrapper;
	LogIn login;
	LogOut logout;
	ProductsCatalog products;
	CartDetails cartDet;
	 
	String userN, psw;
	
	@BeforeTest
	public void beforeTest() {
		seleniumWrapper = new SeleniumWrapper(driver);
		driver = seleniumWrapper.chromeDriverConnection();
		login = new LogIn(driver);
		logout = new LogOut(driver);
		products = new ProductsCatalog(driver);
		cartDet = new CartDetails(driver);
		
		//Using Excel
		userN = seleniumWrapper.getCellData("users", 1, 0);
		psw = seleniumWrapper.getCellData("users", 1, 1);
	}
	
	@Test
	public void f() throws IOException {
		//step1 - open browser and go to app
		seleniumWrapper.launchBrowser(GlobalVariables.QA_URL);
						
		//step2 - Login
		login.loginSaucedemo(userN, psw, "TC005");
		login.validateLogin("TC005");
						
		//step3 - Add multiple items to the cart and validate it.
		products.addProductCart(3, "TC005");
		cartDet.validateProductsOnCart();
				
		//step4 - Logout
		logout.logoutSaucedemo();	
	}
  
	@AfterTest
	public void afterTest() {
		logout.closeAppSaucedemo();
	}
	
}//close class
