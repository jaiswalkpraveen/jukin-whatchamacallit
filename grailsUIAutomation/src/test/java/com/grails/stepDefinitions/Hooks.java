package com.grails.stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.grails.helpers.TestContext;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	 TestContext testContext;
	 static WebDriver driver;
	 
	 public Hooks(TestContext context) {
		 testContext = context;		 
	 }
	 
		 @Before
		 public void BeforeSteps() {
			 driver = testContext.getWebDriverManager().getDriver();
		 }
	 	
	 	@After
	 	public void tearDown(Scenario scenario) {
	 	    if (scenario.isFailed()) {
	 	      final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	 	      scenario.embed(screenshot, "image/png"); 	 	    
	 	    }
	 	   testContext.getWebDriverManager().closeDriver();
	 	   
	 	}
	 

}
