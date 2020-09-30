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

	@Then("^user validate register page link \"([^\"]*)\" and title \"([^\"]*)\"$")
	public void user_validate_register_page_link_and_title(String link, String title){
		registerPage.validateRegisterPage(link,title);
	}
	
	@When("^user enter email as \"([^\"]*)\", password as \"([^\"]*)\" and confirmation password as \"([^\"]*)\"$")
	public void user_enter_email_as_password_as_and_confirmation_password_as(String email, String password, String confirmationPwd){
		registerPage.enterCredential(email, password, confirmationPwd);
	}

	@When("^click on submit button$")
	public void click_on_submit_button(){
		registerPage.pressRegister();
	}

	@Then("^user should shown success message as \"([^\"]*)\"$")
	public void user_should_shown_success_message_as(String successMsg){
		registerPage.validateSuccessRegister(successMsg);
	}

	@Then("^user should get validation \"([^\"]*)\"$")
	public void user_should_get_validation(String validationMsg){
		registerPage.validateIncorrectRegistrationText(validationMsg);
	}
}
