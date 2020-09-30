package com.grails.pageObjects;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.grails.managers.FileReaderManager;

public class BaseTestMethods {
	static WebDriver driver;
	private static WebDriverWait wait;
	
	public BaseTestMethods(WebDriver driver) {
		BaseTestMethods.driver = driver;
		wait = new WebDriverWait(driver, FileReaderManager.getInstance().getConfigReader().getExplicitlyWait());
	}


	static void click(WebElement element)
	{
		WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		clickableElement.click();
	}
		
	static WebElement waitForElementToVisible(WebElement element) {
		 WebElement activeElement =  wait.until(ExpectedConditions.visibilityOf(element));
		 return activeElement;
	}
	
	static String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	static void assertEqual(String actualMsg, String expectedMsg, String exceptionMsg) {
		Assert.assertEquals(actualMsg, expectedMsg, exceptionMsg);
	}
	
	static void typeInput(WebElement element, String input) {
        try {
            if (element != null && element.isEnabled()) {
            	element.sendKeys(input);
            }
          } catch (StaleElementReferenceException e) {
            e.printStackTrace();
          }
		
	}
	
	static void assertURL(String currentURI, String expectedURI, String exceptionMsg ) {
		Assert.assertTrue(currentURI.contains(expectedURI), exceptionMsg);	
	}  
}
