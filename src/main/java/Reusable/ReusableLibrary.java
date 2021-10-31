package Reusable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.factory.DriverFactory;

public class ReusableLibrary {

	private WebDriver driver;
	private DriverFactory driverFactory;
	
	public void SetBrowser() {
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(driverFactory.BrowserName);
	}
	
	public void QuitBrowser() {
		driver.quit();
	}
	/*
	 * Method to open the navigate to the url
	 * 
	 * @parameter: url
	 */
	public void OpenUrl(String url) {
		this.driver = DriverFactory.getDriver();
		driver.get(url);
	}

	/*
	 * Method to verify the element presence
	 * 
	 * @parameter: xpath
	 * 
	 * @return: boolean
	 */
	public boolean isDisplayed(By xpath) {
		// Scroll to make the element into view
		WebElement element = driver.findElement(xpath);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		if (driver.findElement(xpath).isDisplayed())
			return true;
		else {
			return false;
		}
	}

	/*
	 * Method to perform click action using element.click
	 * 
	 * @parameter: xpath, Description
	 */
	public void ClickElement(By xpath, String Description) {
		if (isDisplayed(xpath)) {

			driver.findElement(xpath).click();
			System.out.println("'" + Description + "'" + " Element is clicked");
		} else {
			System.out.println("'" + Description + "'" + " Element is not displayed");
		}
	}

	/*
	 * Method to perform click action using javascript executor
	 * 
	 * @parameter: xpath
	 */
	public void ClikcElementByJS(By xpath) {
		if (isDisplayed(xpath)) {
			WebElement element = driver.findElement(xpath);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			System.out.println("Element is not displayed");
		}
	}

	/*
	 * Method to create xpath using template
	 * 
	 * @parameter: xpathTemplate and reference
	 * 
	 * @return xpath
	 */
	public By GetXpath(String xpath, String ref) {
		return By.xpath(xpath.replaceFirst("<<[a-zA-Z]+>>", ref));

	}

	/*
	 * Method to enter text
	 * 
	 * @parameter: xpath
	 */
	public void EnterText(By xpath, String val) {
		driver.findElement(xpath).sendKeys(val);
	}

	/*
	 * Method to count the xpaht occurrence
	 * 
	 * @parameter: xpath
	 * 
	 * @return: occurrence
	 */
	public int ElementCount(By xpath) {

		return driver.findElements(xpath).size();
	}
}
