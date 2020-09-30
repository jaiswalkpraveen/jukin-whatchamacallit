#Author: Praveen Jaiswal
Feature: Automate registration flow

	Background: 
 		Given user is on the loginpage
	    When user click on register link
	    Then user validate register page link "WhatChaMaCallIt/login/register" and title "Fill out the form to create your account."
	    	    
	@smoke
   Scenario: Register with valid credential
	    When user enter email as "praveen@sting.com", password as "Fuller@123" and confirmation password as "Fuller@123"
	    And  click on submit button
	    Then user should shown success message as "Check your email for account verification instructions."
	    
	    
	@smoke
   Scenario Outline: Register with invalid credential combination
	    When user enter email as "<invalidMail>", password as "<password>" and confirmation password as "<confirmationPassword>"
	    And  click on submit button
	    Then user should get validation "<error>"
	    
	    Examples: 
	    |invalidMail				|password	|confirmationPassword	|error											|
	    |quality@jukinmedia.com		|Zeigler	|Zeigler				|Email not available.							|
	    |praveen					|Zeigler	|Zeigler				|Please enter a valid Email.					|   
	    |praveen@gmail				|Zeigler	|Zeigler				|Please enter a valid Email.					| 
	    |praveen@.com				|Zeigler	|Zeigler				|Please enter a valid Email.					|
	    |praveen					|Zeigler	|Zeigler123				|Password confirmation must must match Password	|
	    |praveen@gmail.com			|			|						|Please fill out all fields						|			