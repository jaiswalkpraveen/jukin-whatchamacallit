package com.grails.stepDefinitions;

import com.grails.helpers.TestContext;
import com.grails.pageObjects.ForgotPasswordPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ForgotPasswordPageSteps {
	
	TestContext testContext;
	ForgotPasswordPage forgotPasswordPage;
	
	public ForgotPasswordPageSteps(TestContext context) {
		testContext = context;
		forgotPasswordPage = testContext.getPageObjectManager().getForgotPasswordPage();
	}	
	
	@When("^user click on forget password link$")
	public void user_click_on_forget_password_link(){
		forgotPasswordPage.navigateForgotPwdPage();
	}

	@Then("^user validate forgot password page link \"([^\"]*)\"$")
	public void user_validate_page(String link){
		forgotPasswordPage.validateForgotPwdPage(link);
	}

	@When("^user enter mail \"([^\"]*)\"$")
	public void user_enter_mail(String email){
		forgotPasswordPage.enterMailID(email);
	}

	@When("^click on Submit button$")
	public void click_on_Submit_button() {
		forgotPasswordPage.pressSubmit();
	}
	
	@Then("^user validate text \"([^\"]*)\" and \"([^\"]*)\" in alert box$")
	public void user_validate_text_and_in_alert_box(String title, String body){
		forgotPasswordPage.validateForgotPwdText(title, body);
	}
	
	@Then("^user should shown error message \"([^\"]*)\"$")
	public void user_should_shown_error_message(String error){
		forgotPasswordPage.validateForgotPwdInvalidEmail(error);
	}

	
	
	


}
