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

public class ForgotPasswordPage {
	WebDriver driver;
	WebDriverWait wait = null;
	Faker faker = new Faker();
	
	public ForgotPasswordPage(WebDriver driver) { 
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, FileReaderManager.getInstance().getConfigReader().getExplicitlyWait());
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
	 * @throws InterruptedException 
	 */


	public void navigateForgotPwdPage() {
		fpLink.click();		
	}

	public void validateForgotPwdPage(String expectedLink) {
		String forgotPwdLink = driver.getCurrentUrl();
		Assert.assertTrue(forgotPwdLink.contains(expectedLink), "user is not on forgot password page");		
	}

	public void enterMailID(String email) {
		fpEmailTextField.sendKeys(email);		
	}

	public void pressSubmit() {
		fpSubmitButton.click();
	}

	public void validateForgotPwdText(String expTitle, String expBody) {
		wait.until(ExpectedConditions.visibilityOf(fpSuccessModal));
		Assert.assertEquals(fpSuccessTitle.getText(), expTitle, "Incorrect forgot password title" );
		Assert.assertEquals(fpSuccessBody.getText(), expBody, "Incorrect forgot password body" );
		fpCloseModal.click();	
	}


	public void validateForgotPwdInvalidEmail(String error) {
		Assert.assertEquals(fpWrongMailText.getText(), error, "Invalid forgot password validation message" );	
	}
}
