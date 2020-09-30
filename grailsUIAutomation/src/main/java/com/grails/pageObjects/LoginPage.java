package com.grails.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.grails.managers.FileReaderManager;

public class LoginPage extends BaseTestMethods {
		
	 public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
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
	 */
	public void launchLoginPage() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl("url"));	
	}

	public void enterLoginCredential(String username, String password) {
		waitForElementToVisible(successLoginText);
		typeInput(usernameTextField, username);
		typeInput(passwordTextField, password);		
	}

	public void pressLogin() {
		click(loginButton);		
	}

	public void validateSuccessLogin(String expectedMessage) {	
		assertEqual(successLoginText.getText(), expectedMessage, "Invalid credential");
	}	
	
	public void validateFailLogin(String expectedMessage) {
		assertEqual(failLoginText.getText(), expectedMessage, "user didn't get warning for wrong cred");	
	}
}
