package com.grails.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BaseTestMethods{
	
	 public RegisterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
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
	private WebElement regFailTextArea;
	
	@FindBy(tagName = "h3")  
	private WebElement successRegisterText;
	
	 /**
	 * Application functions.
	 */

	public void naviagteToRegisterPage() {
		registerLink.click();		
	}

	public void validateRegisterPage(String expectedURI, String title) {
		assertURL(getCurrentURL(), expectedURI, "User is not on register page");		
		assertEqual(successRegisterText.getText(), title, "register page not loaded yet");
	}

	public void enterCredential(String email, String password, String confirmationPwd) {
			typeInput(regEmailTextField, email);
			typeInput(regPasswordTextField, password);
			typeInput(regConfirmPasswordTextField, confirmationPwd);
	}

	public void pressRegister() {
		regSubmitButton.click();		
	}

	public void validateSuccessRegister(String successMsg) {
		assertEqual(successRegisterText.getText(), successMsg, "user couldn't register with valid credential");	
	}

	public void validateIncorrectRegistrationText(String errorTxt) {
		waitForElementToVisible(regFailTextArea);
		assertEqual(regFailTextArea.getText(), errorTxt, "invalid registration text mismatch");		
	}
}
