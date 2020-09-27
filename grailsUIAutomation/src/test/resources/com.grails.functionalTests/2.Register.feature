#Author: Praveen Jaiswal
Feature: Automate registration flow

	Background: 
 		Given user is on the loginpage
	    When user click on register link
	    Then user validate register page link "WhatChaMaCallIt/login/register"
	    	    
	@smoke
   Scenario: Register with valid credential
	    When user enter mailID and password "Fuller@123"
	    And  click on register Submit button
	    Then user should see "Check your email for account verification instructions."
	    
	    
	@smoke   
   Scenario Outline: Register with invalid credential combination
	    When user enter "<invalid>" mailID and "<password>"
	    And  click on register Submit button
	    Then user should see validation "<error>"
	    
	    Examples: 
	    |invalid					|password	|error								|
	    |quality@jukinmedia.com			|match		|Email not available.						|
	    |praveen					|match		|Please enter a valid Email.					|   
	    |praveen@gmail				|match		|Please enter a valid Email.					| 
	    |praveen@.com				|match		|Please enter a valid Email.					|
	    |praveen					|mismatch	|Password confirmation must must match Password			|
	    |praveen@gmail.com				|null		|Please fill out all fields					|			
