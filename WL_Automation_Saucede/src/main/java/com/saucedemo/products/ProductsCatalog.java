package com.saucedemo.products;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.saucedemo.base.SeleniumWrapper;

public class ProductsCatalog extends SeleniumWrapper 
{
	
	public ProductsCatalog(WebDriver driver) {
		super(driver);
	}
	
	By products_txt = By.xpath("//span[contains(text(),'Products')]");
	By footer_div = By.xpath("//div[@class='footer_copy']");
	By productName_div = By.xpath("//div[@class='inventory_item_name']");
	By productPrice_div = By.xpath("//div[@class='inventory_item_price']");
	By addCartProd_btn = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']");
	By picProd_img = By.xpath("//img[@class='inventory_item_img']");
	By descProd_div = By.xpath("//div[@class='inventory_item_desc']");
	By sorterProd_select = By.xpath("//select[@class='product_sort_container']");
	By numItemsCart_txt = By.xpath("//span[@class='shopping_cart_badge']");
		
	/**
	 * Get product title
	 * @param locator
	 * @return string title
	 */
	public String getProdLabel() {
		String textHeader = getTextOf(products_txt);
		return textHeader;
		
	}//close product name
	
	/**
	 * Get product name
	 * @param locator
	 * @return nameProd
	 */
	public String getProductName(By locator) {
		String nameProd = getTextOf(locator);
		return nameProd;
		
	}//close product name
	
	/**
	 * Get product price
	 * @param locator
	 * @return priceProd
	 */
	public String getProductPrice(By locator) {
		String priceProd = getTextOf(locator);
		return priceProd;
		
	}//close product price
	
	/**
	 * Get product desc
	 * @param locator
	 * @return descProd
	 */
	public String getProductDesc(By locator) {
		String descProd = getTextOf(locator);
		return descProd;
		
	}//close product desc
	
	/**
	 * Add product by name
	 * @param numItems,testcase
	 * @throws IOException 
	 */
	public void addProductByName(String nameProd, String testcase) throws IOException {
		reporterLog("Adding an specific product by name...");
		String pathBtn = "//div[@class='inventory_item']/div[@class='inventory_item_description']/div[@class='inventory_item_label']/a/div[@class='inventory_item_name' and contains(text(),'"+nameProd+"')]/../../../div[@class='pricebar']/button";
		findWebElement(By.xpath(pathBtn)).click();
		implicitlyWait(25);
		takeScreenshot(testcase, "product");
		clickOn(numItemsCart_txt);
		
	}//close add product
	
	/**
	 * Add product to cart
	 * @param numItems,testcase
	 * @throws IOException 
	 */
	public void addProductCart(int numItems, String testcase) throws IOException {
		List<WebElement> elements = findWebElementS(addCartProd_btn, "addButtons");
		int x=1;
		
		for(WebElement btnAdd : elements) {
			if(x <= numItems) {
				btnAdd.click();
				implicitlyWait(6);
				reporterLog("Adding product to the cart");
				takeScreenshot(testcase, "addProducto"+x);
			}
			
			x++;
		}
		
		//
		validateNumItemsAdded(x,testcase);
		
	}//close add product
	
	/**
	 * Validate products added
	 * @param countN,testcase
	 * @throws IOException 
	 */
	public void validateNumItemsAdded(int countN, String testcase) throws IOException {
		String itemsCart = getTextOf(numItemsCart_txt);
		String countNum = String.valueOf(itemsCart);
		hardAssertion(itemsCart, countNum);
		reporterLog("The num of items are correct ");
		clickOn(numItemsCart_txt);
		implicitlyWait(25);
		takeScreenshot(testcase, "numItemsAdded");
		
	}//close validate products added
	
	/**
	 * Sort products
	 * @param sortType, locator
	 * @throws IOException 
	 */
	public void sortProducts(String sortType, String testcase) throws IOException {
		reporterLog("Sorting products by: " + sortType);
		selectByValueDropdown(sorterProd_select, sortType); 
		implicitlyWait(6);
		takeScreenshot(testcase, "sortedProducts");
		implicitlyWait(25);
		
	}//close add product
	
	/**
	 * Get attributes products
	 * @param testcase, typeElements
	 *  
	 */
	public void getProductAttr(String testcase, String typeElements) {
		reporterLog("Getting the " + typeElements + " of the products");
		By locator = null;
		
		if(typeElements.equals("Prices")) {
			locator = productPrice_div;
		}
		
		List<WebElement> elements = findWebElementS(locator, typeElements);
		reporterLog(elements.size() + "is the # of elements");
		
		for (int i=0; i<elements.size();i++){
			//System.out.println(typeElements + "=" + elements.get(i).getText());
		}
		
		String sortPrice = verifySort(elements);
		
		hardAssertion(sortPrice, "SORT is working");
		reporterLog("Sorting by price is working !!");
		
	}//close get product attributes
	
	/**
	 * Verify sort is working
	 * @param elements
	 *  
	 */
	public String verifySort(List<WebElement> elements) {
		double list[] = new double[elements.size()];
		String sortP = "";
		
		for(int x=0; x<elements.size(); x++){
			String p = elements.get(x).getText().replace("$", "");
			list[x]= Double.parseDouble(p);
		}
		
		boolean sortFunctionality = true;
		
		outer: 
		for(int i=0; i<list.length; i++)
		{	
			for(int j=i+1; j<list.length; j++)
			{	
				double result = list[i]-list[j];
				//System.out.println("result: " +list[i]+":-:"+ list[j] + " resta: " + result);
				
				if((result > 0)){
					//System.out.println("Data in the Table is not SORTED::" +list[j]+":::"+ list[i]);
					sortFunctionality=false;
					break outer;
				}else{
					 //System.out.println("Data in the Table is SORTED::" +list[j]+":::"+ list[i]);
				}
			}
		}		
		
		if(sortFunctionality){
			sortP = "SORT is working";
		}else{
			sortP = "SORT is not working";
		}
		
		return sortP;
		
	}//close verify sort
	
}//close class
