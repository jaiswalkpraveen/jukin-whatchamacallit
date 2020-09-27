#Author: Praveen Jaiswal
Feature: Automate login flow

Background: 
 Given user is on the loginpage
 
    
	@smoke
   Scenario: Login into application with valid credential
	    When  user enter "quality@jukinmedia.com" and "Test1ng"
	    And click on Login Button
	    Then user should shown "You have successfully logged in."
	
	@smoke   
   Scenario Outline: Login into application with invalid credential
	    When  user enter "<username>" and "<password>"
	    And click on Login Button
	    Then user should get "<outcome>"
	    Examples: 
	    |username					|password				|outcome								|
	    |praveen					|					|Incorrect email/password combination	|
	    |praveen					|lovely					|Incorrect email/password combination	|			
