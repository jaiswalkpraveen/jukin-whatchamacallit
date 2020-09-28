package com.grails.managers;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.grails.enums.DriverType;
import com.grails.enums.EnvironmentType;
import com.grails.enums.OSType;;


public class WebDriverManager {
	private WebDriver driver;
	private static DriverType driverType;
	private static OSType osType;
	private static EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
		osType = OSType.valueOf(System.getProperty("os.name").toUpperCase());
	}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
		switch (environmentType) {	    
	        case LOCAL : driver = createLocalDriver();
	        	break;
	        case REMOTE : driver = createRemoteDriver();
	        	break;
		}
	return driver;
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	private WebDriver createLocalDriver() {
        switch (driverType) {	    
        case FIREFOX : driver = new FirefoxDriver();
	    	break;
        case CHROME :        	
        	launchOSBasedDriver(osType);
        	driver = new ChromeDriver();
        	driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
    		break;
        case INTERNETEXPLORER : driver = new InternetExplorerDriver();
    		break;
        }
		return driver;
	}	

	private void launchOSBasedDriver(OSType osType) {
    	switch (osType) {
        case LINUX: 
        	System.setProperty(CHROME_DRIVER_PROPERTY, System.getProperty("user.dir") + File.separator + FileReaderManager.getInstance().getConfigReader().getDriverPath() + File.separator + "linux" +File.separator + "chromedriver");
        	break;
        case WINDOWS:
        	System.setProperty(CHROME_DRIVER_PROPERTY, System.getProperty("user.dir") + File.separator + FileReaderManager.getInstance().getConfigReader().getDriverPath() + File.separator + "windows" +File.separator + "chromedriver.exe");
        	break;
        case MAC:
        	System.setProperty(CHROME_DRIVER_PROPERTY, System.getProperty("user.dir") + File.separator + FileReaderManager.getInstance().getConfigReader().getDriverPath() + File.separator + "mac" + File.separator + "chromedriver");
        	break;
    	}
	}

	public void closeDriver() {
		driver.close();
		driver.quit();
	}
}