package com.saucedemo.qa;

import org.testng.annotations.Test;

import com.saucedemo.base.GlobalVariables;
import com.saucedemo.base.SeleniumWrapper;
import com.saucedemo.cart.CartDetails;
import com.saucedemo.cart.DestinationInfo;
import com.saucedemo.cart.DetailsPurchase;
import com.saucedemo.cart.PurchaseDone;
import com.saucedemo.common.LogIn;
import com.saucedemo.common.LogOut;
import com.saucedemo.products.ProductsCatalog;

import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class TC007_CompletePurchase {
	WebDriver driver;
	SeleniumWrapper seleniumWrapper;
	LogIn login;
	LogOut logout;
	ProductsCatalog products;
	CartDetails cartDet;
	DestinationInfo destination;
	DetailsPurchase detPurchase;
	PurchaseDone purchaseDone;
	 
	String userN, psw;
	
	@BeforeTest
	public void beforeTest() {
		seleniumWrapper = new SeleniumWrapper(driver);
		driver = seleniumWrapper.chromeDriverConnection();
		login = new LogIn(driver);
		logout = new LogOut(driver);
		products = new ProductsCatalog(driver);
		cartDet = new CartDetails(driver);
		destination = new DestinationInfo(driver);
		detPurchase = new DetailsPurchase(driver);
		purchaseDone = new PurchaseDone(driver);
		
		//Using Excel
		userN = seleniumWrapper.getCellData("users", 1, 0);
		psw = seleniumWrapper.getCellData("users", 1, 1);
	}
	
	@Test
	public void f() throws IOException {
		//step1 - open browser and go to app
		seleniumWrapper.launchBrowser(GlobalVariables.QA_URL);
						
		//step2 - Login
		login.loginSaucedemo(userN, psw, "TC007");
		login.validateLogin("TC007");
						
		//step3 - Add multiple items to the cart and validate it.
		products.addProductCart(2, "TC005");
		cartDet.validateProductsOnCart();
		
		//step4 - Complete checkout and validate it
		cartDet.checkoutPurchase("TC007");
		destination.completeInfo("Viktor", "Orozco", "45000", "TC007");
				
		//step5 - Complete purchase
		detPurchase.finishingPurchase("TC007");
		purchaseDone.validatePurchaseFinished("TC007");
		
		//step6 - Logout
		logout.logoutSaucedemo();	
	}
  
	@AfterTest
	public void afterTest() {
		logout.closeAppSaucedemo();
	}

}//close class
