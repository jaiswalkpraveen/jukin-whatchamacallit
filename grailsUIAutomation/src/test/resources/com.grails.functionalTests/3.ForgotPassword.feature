#Author: Praveen Jaiswal
Feature: Automate forgot password flow

	Background: 
 		Given user is on the loginpage
 		When user click on forget password link
		Then user validate forgot password page link "WhatChaMaCallIt/login/forgotPassword"
	    
	@smoke
	Scenario: Forgot password with valid mail
	    When user enter mail "quality@jukinmedia.com" 
	    And  click on Submit button
	    Then user validate text "Password reset successfully" and "Check your email for password reset instructions." in alert box
	    
	@smoke    
 	Scenario Outline: Forgot password with invalid mail
	    When user enter mail "<mail>" 
	    And  click on Submit button
	    Then user should shown error message "Please enter a valid email"
	    
	    Examples: 
	    |mail					|
	    |						|
	    |praveen@gmail				|
	    |praveen					|			
