package com.grails.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends BaseTestMethods{
	
	 public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	 /**
	 * WebElements of Forgot password Page
	 */	
	
	@FindBy(linkText =  "Forgot Password?")  
	private WebElement fpLink;
	
	@FindBy(id = "fp.email")  
	private WebElement fpEmailTextField;
	
	@FindBy(id = "forgotPasswordButton")  
	private WebElement fpSubmitButton;
	
	@FindBy(id = "fpSuccessModal")  
	private WebElement fpSuccessModal;
		
	@FindBy(id = "fpSuccessModalLabel")  
	private WebElement fpSuccessTitle;
	
	@FindBy(className = "modal-body")  
	private WebElement fpSuccessBody;
	
	@FindBy(xpath = "//div[@class='modal-footer']/button")  
	private WebElement fpCloseModal;
	
	@FindBy(id = "flashMsg")  
	private WebElement fpWrongMailText;

	
	 /**
	 * Application functions.
	 */


	public void navigateForgotPwdPage() {
		click(fpLink);
	}

	public void validateForgotPwdPage(String expectedURI) {
		assertURL(getCurrentURL(), expectedURI, "User is not on forgot password page");			
	}

	public void enterMailID(String email) {
		typeInput(fpEmailTextField, email);	
	}

	public void pressSubmit() {
		click(fpSubmitButton);
	}

	public void validateForgotPwdModalText(String expTitle, String expBody) {
		waitForElementToVisible(fpSuccessModal);
		assertEqual(fpSuccessTitle.getText(), expTitle, "Incorrect forgot password title");
		assertEqual(fpSuccessBody.getText(), expBody, "Incorrect forgot password body" );
		click(fpCloseModal);
	}


	public void validateForgotPwdErrorText(String error) {
		assertEqual(fpWrongMailText.getText(), error, "Invalid forgot password validation message");
	}
}
