package stepDefinitions;

import com.pages.BSAPage;

import Reusable.ReusableLibrary;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EligibilityCheckerStepdef extends ReusableLibrary {

	BSAPage bsa = new BSAPage();

	@Before
	public void setup() {
		SetBrowser();
	}

	@After
	public void teardown() {
		QuitBrowser();
	}

	@When("I click start Now")
	public void i_click_start_now() {
		bsa.startQuestion();

	}

	@Given("I navigate to Eligibilty Checker start page")
	public void i_navigate_to_eligibilty_checker_start_page() {
		bsa.navigateUserTo("https://services.nhsbsa.nhs.uk/check-for-help-paying-nhs-costs/start");
		bsa.acceptCookies();

	}

	@Then("I should access the Eligibilty cheker Questions")
	public void i_should_access_the_eligibilty_cheker_questions() {

	}

	@Given("I am a person from {string}")
	public void i_am_a_person_from_wales(String country) {
		bsa.chooseCountry(country);
	}

	@Given("My date of birth is {string}")
	public void my_date_of_birth_is(String date) {
		bsa.setDob(date);
	}

	@When("I put my circumstances into the checker tool")
	public void i_put_my_circumstances_into_the_checker_tool() {
		bsa.answerAllQuestions();
	}

	@Then("I should get free dental check")
	public void i_should_get_free_dental_check() {
		bsa.checkFreeDental(true);
	}

	@Then("I should not get free dental check")
	public void i_should_not_get_free_dental_check() {
		bsa.checkFreeDental(false);
	}
}
