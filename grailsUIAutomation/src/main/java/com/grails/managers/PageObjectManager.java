package com.grails.managers;

import org.openqa.selenium.WebDriver;

import com.grails.pageObjects.ForgotPasswordPage;
import com.grails.pageObjects.LoginPage;
import com.grails.pageObjects.RegisterPage;

public class PageObjectManager {
	private WebDriver driver;
	private LoginPage loginPage;
	private ForgotPasswordPage forgotPasswordPage;
	private RegisterPage registerPage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage getLoginPage(){
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}
	
	public ForgotPasswordPage getForgotPasswordPage(){
		return (forgotPasswordPage == null) ? forgotPasswordPage = new ForgotPasswordPage(driver) : forgotPasswordPage;
	}
	
	public RegisterPage getRegisterPage(){
		return (registerPage == null) ? registerPage = new RegisterPage(driver) : registerPage;
	}
	
}