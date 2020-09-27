package com.grails.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.javafaker.Faker;
import com.grails.managers.FileReaderManager;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait = null;
	Faker faker = new Faker();
	
	public LoginPage(WebDriver driver) { 
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, FileReaderManager.getInstance().getConfigReader().getExplicitlyWait());
	}
	
	 /**
	 * element locator of login page.
	 */
	
	@FindBy(id = "username")  
	private WebElement usernameTextField;
	
	@FindBy(id = "creds.password")  
	private WebElement passwordTextField;
	
	@FindBy(id = "loginButton")  
	private WebElement loginButton;
	
	@FindBy(tagName = "h3")  
	private WebElement successLoginText;
	
	@FindBy(xpath = "//div[contains(text(),'Incorrect email')]")  
	private WebElement failLoginText;
	


	
	 /**
	 * Application functions.
	 * @throws InterruptedException 
	 */
	public void launchLoginPage() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl("url"));		
	}

	public void enterLoginCredential(String username, String password) {
		wait.until(ExpectedConditions.visibilityOf(successLoginText));
		usernameTextField.sendKeys(username);
		passwordTextField.sendKeys(password);		
	}


	public void pressLogin() {
		loginButton.click();		
	}

	public void validateSuccessLogin(String expectedMessage) {	
		Assert.assertEquals(successLoginText.getText(), expectedMessage, "Invalid credential" );
	}	
	
	public void validateFailLogin(String expectedMessage) {
		Assert.assertEquals(failLoginText.getText(), expectedMessage, "user didn't get warning for wrong cred" );	
	}
}
