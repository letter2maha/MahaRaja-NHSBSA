package com.qa.factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	public static String BrowserName;

	/**
	 * This method will be used to initialise the browser details
	 * 
	 * @param browser
	 * @return this will return threadLocalDriver.
	 */
	public WebDriver init_driver(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			threadLocalDriver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			threadLocalDriver.set(new FirefoxDriver());
		} else {
			System.out.println("Provide correct browser value: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return getDriver();

	}

	/**
	 * Local driver details will be obtained using this method
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return threadLocalDriver.get();
	}
}
