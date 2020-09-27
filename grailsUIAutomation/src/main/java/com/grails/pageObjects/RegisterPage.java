package com.grails.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.javafaker.Faker;
import com.grails.managers.FileReaderManager;

public class RegisterPage {
	WebDriver driver;
	WebDriverWait wait = null;
	Faker faker = new Faker();
	
	public RegisterPage(WebDriver driver) { 
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, FileReaderManager.getInstance().getConfigReader().getExplicitlyWait());
	}
	
	 /**
	 * WebElement locator of Registration Page
	 */
	@FindBy(linkText =  "Register")  
	private WebElement registerLink;
	
	@FindBy(id = "reg.email")  
	private WebElement regEmailTextField;
	
	@FindBy(id = "reg.password")  
	private WebElement regPasswordTextField;
	
	@FindBy(id = "reg.confirmPassword")  
	private WebElement regConfirmPasswordTextField;
	
	@FindBy(name = "_action_doRegister")  
	private WebElement regSubmitButton;
	
	@FindBy(id = "failedMessage")  
	private WebElement regFailText;
	
	@FindBy(tagName = "h3")  
	private WebElement successRegisterText;
	
	 /**
	 * Application functions.
	 * @throws InterruptedException 
	 */

	public void naviagteToRegisterPage() {
		registerLink.click();		
	}


	public void validateRegisterPage(String expectedLink) {
		String registerLink = driver.getCurrentUrl();
		Assert.assertTrue(registerLink.contains(expectedLink), "user is not on registration page");	
	}


	public void enterRegisterCredential(String password) {
		regEmailTextField.sendKeys(faker.internet().emailAddress());	
		regPasswordTextField.sendKeys(password);
		regConfirmPasswordTextField.sendKeys(password);
	}


	public void pressRegister() {
		regSubmitButton.click();		
	}

	public void validateSucessRegister(String successMsg) {
		Assert.assertEquals(successRegisterText.getText(), successMsg, "user couldn't register" );		
	}

	private void enterRegPassword(String password, String confirmPassword) {
		regPasswordTextField.sendKeys(password);
		regConfirmPasswordTextField.sendKeys(confirmPassword);
	}

	public void enterRegInvalidCred(String email, String pwdType) {
		String password = faker.internet().password();
		regEmailTextField.sendKeys(email);
		if(pwdType.equalsIgnoreCase("mismatch")) {
			enterRegPassword(password, password+"mis");
		}
		else if(pwdType.equalsIgnoreCase("null")) {
			enterRegPassword("", "");
		}
		else if(pwdType.equalsIgnoreCase("match")){
			enterRegPassword(password, password);
		}
	}


	public void validateIncorrectRegistrationText(String errorTxt) {
		Assert.assertEquals(regFailText.getText(), errorTxt, "invalid registration text" );		
	}
}
