package MyRunner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.factory.DriverFactory;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// configured cucumber features and report generation plugin in cucumber option
@CucumberOptions(

		features = "src/test/resources/Features", glue = { "stepDefinitions" }

		, plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/" }

		, monochrome = true, publish = true

)


public class Runner extends AbstractTestNGCucumberTests {


	/** 
	 * Method used to initialise browser details 
	 * @param browsername
	 */
	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(@Optional("chrome") String browsername) {
		System.out.println("---------------------------" + browsername);
		DriverFactory.BrowserName = browsername;
	}
}