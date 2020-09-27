package com.grails.stepDefinitions;

import com.grails.helpers.TestContext;
import com.grails.pageObjects.LoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPageSteps {
	
	TestContext testContext;
	LoginPage loginPage;
		
	public LoginPageSteps(TestContext context) {
		testContext = context;
		loginPage = testContext.getPageObjectManager().getLoginPage();
	}
	
	@Given("^user is on the loginpage$")
	public void loginPage() {	
		loginPage.launchLoginPage();
	}

	@When("^user enter \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_and(String username, String password){
		loginPage.enterLoginCredential(username, password);
	}

	@When("^click on Login Button$")
	public void click_on_Login_Button() {
		loginPage.pressLogin();
	}

	@Then("^user should shown \"([^\"]*)\"$")
	public void user_should_shown_message(String message){
		loginPage.validateSuccessLogin(message);
	}
	
	@Then("^user should get \"([^\"]*)\"$")
	public void user_should_shown_error(String error){
		loginPage.validateFailLogin(error);
	}
}
