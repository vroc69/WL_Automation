package com.saucedemo.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class SeleniumWrapper 
{
	private WebDriver driver;
	
	/**
	 * Constructor
	 * @author VROC
	 * @param driver
	 */
	public SeleniumWrapper(WebDriver driver) {
		this.driver = driver;
	}//close Consctructor
	
	/**
	 * Chrome driver conn
	 * @author VROC
	 * @param
	 */
	public WebDriver chromeDriverConnection() {
		System.setProperty(GlobalVariables.CHROME_DRIVER,GlobalVariables.PATH_CHROME_DRIVER);
		driver = new ChromeDriver();
		return driver;
		
	}//close chrome conn
	
	/**
	 * Launch Browser
	 * @author VROC
	 * @param url
	 * @throws IOException
	 */
	public void launchBrowser(String url) throws IOException {
		try {
			reporterLog("Launching...." + url);
			driver.get(url);
			driver.manage().window().maximize();
			takeScreenshot("TC000","lunchBrowser");
			implicitlyWait(5);
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
				
	}//close launch browser
	
	/**
	 * Reporter Log
	 * @author VROC
	 * @param logTxt
	 * 
	 */
	public void reporterLog(String logTxt) {
		Reporter.log(logTxt);
		
	}// reporter log
	
	/**
	 * Implicitly Wait
	 * @author VROC
	 * @param seconds
	 * 
	 */
	public void implicitlyWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		
	}//close implicitly wait
	
	/**
	 * Move to Web Element
	 * @author VROC
	 * @param locator
	 * 
	 */
	public void moveToWebElement(By locator) {
		WebElement element = driver.findElement(locator);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		actions.release().perform();
		
	}//close move to element
	
	/**
	 * Take Screenshot
	 * @author VROC
	 * @param testCase, fileName
	 * @throws IOException
	 */
	public void takeScreenshot(String testCase, String fileName)throws IOException {
		try {
			Screenshot screenshot = new AShot().takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(),"PNG",new File(GlobalVariables.PATH_SCREENSHOTS + testCase + "/" + fileName +".png"));
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
		
	}//close take screenshot
	
	/**
	 * Close Browser
	 * @author VROC
	 * 
	 * 
	 */
	public void closeBrowser() {
		try {
			reporterLog("Close Browser");
			driver.quit();
			driver.close();			
		} catch(NoSuchSessionException e) {
			reporterLog("Closing Browser is failed");
		}
	}
	
	/**
	 * Find Web Element
	 * @author VROC
	 * @param locator
	 * @return Web Element
	 * 
	 */
	public WebElement findWebElement(By locator) {
		try {
			return driver.findElement(locator);
		}catch(NoSuchElementException e) {
			return null;
		}
		
	}//close find element
	
	/**
	 * Find List of Web Element
	 * @author VROC
	 * @param locator
	 * @return List Web Element
	 * 
	 */
	public List<WebElement> findWebElementS(By locator, String webElements) {
		try {
			List<WebElement> elements = driver.findElements(locator);
			return elements;
		}catch(NoSuchElementException e) {
			return null;
		}
		
	}//close find list element
	
	/**
	 * Check enable element
	 * @author VROC
	 * @param locator
	 * 
	 */
	public void elementExist(By locator) {
		try {
			Assert.assertTrue(driver.findElement(locator).isEnabled());
			Assert.assertTrue(driver.findElement(locator).isDisplayed());
		}catch(AssertionError e) {
			Assert.fail("The element doesn't exist !!");
		}		
	}//close element exist
	
	/**
	 * Click on web element
	 * @author VROC
	 * @param locator
	 * 
	 */
	public void clickOn(By locator) {
		driver.findElement(locator).click();
		
	}//close click
	
	/**
	 * Get text of web element
	 * @author VROC
	 * @param locator
	 * @return object value
	 * 
	 */
	public String getTextOf(By locator) {
		try {
			return driver.findElement(locator).getText();
		}catch(NoSuchElementException e) {
			return null;
		}
		
	}//close get text
	
	/**
	 * Write on element
	 * @author VROC
	 * @param locator, inputText
	 * 
	 */
	public void writeOn(By locator, String inputText) {
		Reporter.log("Typing text: " + inputText);
		driver.findElement(locator).sendKeys(inputText);
		
	}//close write on element
	
	/**
	 * Wait for Element present
	 * @author VROC
	 * @param locator
	 * 
	 */
	public void waitForElementPresent(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,GlobalVariables.GENERAL_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}catch(TimeoutException e) {
			throw new IllegalStateException("Web Element not found !!");
		}
		
	}//close wait for element
	
	/**
	 * Select Value Dropdown
	 * @author VROC
	 * @param locator,value
	 * 
	 */
	public void selectByValueDropdown(By locator, String value ) {
		waitForElementPresent(locator);
		WebElement element = driver.findElement(locator);
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
		
	}//close select dropdown
	
	/**
	 * Hard Assertion 2 texts
	 * @author VROC
	 * @param expectedValue, actualValue
	 * 
	 */
	public void hardAssertion(String actualValue, String expectedValue) {
		try {
			Assert.assertEquals(actualValue,expectedValue);
		}catch(AssertionError e) {
			Assert.fail("Not able to assert Actual value: " + actualValue + " || " + "Expected value: " + expectedValue);
		}
		
	}// close hard assertion
	
	/**
	 * Assertion correct if true
	 * @author VROC
	 * @param condition
	 * 
	 */
	public void assertCorrect(boolean condition) {
		try {
			Assert.assertTrue(condition);
		}catch(AssertionError e) {
			Assert.fail("The condition is not true");
		}
		
	}// close hard assertion
	
	/*
	* Get text from table html
	* @author VROC
	* @param row, column
	*/
	public String getValueFromTableHTML(String row, String column) 
	{
		try {
			return driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+column+"]")).getText();
		}catch(NoSuchElementException e) {
			return null;
		}
		
	}//close get text from table html
	
	/**
	 * Get Data from JSON file 1 node 
	 * @author VROC
	 * @param jsonFile, jsonObjName, jsonKey
	 * @return jsonValue
	 * @throws FileNotFoundException
	 */
	public String getJSONValue(String jsonFile, String jsonObjName, String jsonKey) throws FileNotFoundException 
	{
		try {
 
			// JSON Data
			InputStream inputStream = new FileInputStream(GlobalVariables.PATH_JSON_DATA + jsonFile + ".json");
			JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));
 
			// Get Data
			String jsonValue = (String) jsonObject.getJSONObject(jsonObjName).get(jsonKey);
			return jsonValue;
 
		} catch (FileNotFoundException e) {
			Assert.fail("JSON file is not found");
			return null;
		}//close try-catch
		
	}//close get data from json 1 node
	
	/**
	* Get Data from JSON file
	* @author VROC
	* @param jsonFile, jsonKey
	* @return jsonValue
	* @throws FileNotFoundException
	*/
	public String getJSONValue(String jsonFileObj, String jsonKey) throws FileNotFoundException 
	{
		try 
		{
			// JSON Data
			InputStream inputStream = new FileInputStream(GlobalVariables.PATH_JSON_DATA + jsonFileObj + ".json");
			JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));
			
			// Get Data
			String jsonValue = (String) jsonObject.getJSONObject(jsonFileObj).get(jsonKey);
			return jsonValue;
			
		 } catch (FileNotFoundException e) {
			 Assert.fail("JSON file is not found");
			 return null;
		 }
		
	}//close get json data
 
	/*
	 * Get Value from Excel cell
	 * @author VROC
	 * @date excelName, row, column
	 * @throws FileNotFoundException
	 */
	public String getCellData(String excelName, int row, int column) 
	{
		try {
			// Reading Data
			FileInputStream fis = new FileInputStream(GlobalVariables.PATH_EXCEL_DATA+excelName+".xlsx");
			// Constructs an XSSFWorkbook object
			@SuppressWarnings("resource")
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			Row rowObj = sheet.getRow(row);
			Cell cell = rowObj.getCell(column);
			String value = cell.getStringCellValue();
			return value;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}//close try-catch
		
	}//close get excel data cell
		
}//close class
