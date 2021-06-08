package com.saucedemo.cart;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.saucedemo.base.SeleniumWrapper;

public class CartDetails extends SeleniumWrapper 
{

	public CartDetails(WebDriver driver) {
		super(driver);
	}
	
	By numItemsCart_txt = By.xpath("//a/span[@class='shopping_cart_badge']");
	By removeItem_btn = By.xpath("//button[@class='btn btn_secondary btn_small cart_button']");
	By continueShopping_btn = By.xpath("//button[@id='continue-shopping']");
	By checkout_btn = By.xpath("//button[@id='checkout']");
	By cartItem_container = By.xpath("//div[@class='cart_item']");	
	By prodName_txt = By.xpath("//div[@class='inventory_item_name']");
	
	/**
	 * Validate products on cart
	 * @param 
	 * @throws IOException 
	 */
	public void validateProductsOnCart() {
		boolean itemsCorrect = false;
		List<WebElement> elements = findWebElementS(cartItem_container, "items");
		String itemsCart = getTextOf(numItemsCart_txt);
		int countNum = Integer.parseInt(itemsCart);
		
		//System.out.println("elements: " + elements.size());
		//System.out.println("countN: " + countNum);
		
		if(elements.size() == countNum) {
			itemsCorrect = true;
		}
		
		assertCorrect(itemsCorrect);
		reporterLog("The products in the cart are correct !!");
		
	}//close validate products on cart
	
	/**
	 * Validate a product on cart
	 * @param 
	 * @throws IOException 
	 */
	public void validateSpecificProduct(String prodName, String testcase) throws IOException {
		List<WebElement> elements = findWebElementS(prodName_txt,"nameProducts");
		
		for(WebElement txtName : elements) {
			String name = txtName.getText();
			takeScreenshot(testcase, "cartDetails");
			reporterLog("Correct product added !!");
			hardAssertion(prodName, name);
		}
		
	}//close validate a product
	
	/**
	 * Go to checkout
	 * @param testcase
	 * @throws IOException 
	 */
	public void checkoutPurchase(String testcase) throws IOException {
		reporterLog("Go to checkout screen....");
		clickOn(checkout_btn);
		implicitlyWait(25);
		takeScreenshot(testcase, "checkoutScreen");
		
	}//close go to checkout
}//close class
