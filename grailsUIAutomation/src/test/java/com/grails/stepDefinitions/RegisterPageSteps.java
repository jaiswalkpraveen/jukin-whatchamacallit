package com.grails.stepDefinitions;

import com.grails.helpers.TestContext;
import com.grails.pageObjects.RegisterPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterPageSteps {
	
	TestContext testContext;
	RegisterPage registerPage;
	
	public RegisterPageSteps(TestContext context) {
		testContext = context;
		registerPage = testContext.getPageObjectManager().getRegisterPage();
	}
	
	@When("^user click on register link$")
	public void user_click_on_register_link(){
		registerPage.naviagteToRegisterPage();
	}

	@Then("^user validate register page link \"([^\"]*)\"$")
	public void user_validate_register_page_link(String link){
		registerPage.validateRegisterPage(link);
	}

	@When("^user enter mailID and password \"([^\"]*)\"$")
	public void user_enter_mailID_and_password(String password) {
		registerPage.enterRegisterCredential(password);
	}

	@When("^click on register Submit button$")
	public void click_on_register_Submit_button() {
		registerPage.pressRegister();
	}

	@Then("^user should see \"([^\"]*)\"$")
	public void user_should_see(String successMsg){
		registerPage.validateSucessRegister(successMsg);
	}
	
	@When("^user enter \"([^\"]*)\" mailID and \"([^\"]*)\"$")
	public void user_enter_mailID_and(String email, String pwdType){
		registerPage.enterRegInvalidCred(email, pwdType);
	}

	@Then("^user should see validation \"([^\"]*)\"$")
	public void user_should_see_validation(String errorTxt){
		registerPage.validateIncorrectRegistrationText(errorTxt);
	}
}
