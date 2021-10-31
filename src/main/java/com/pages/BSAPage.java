package com.pages;

import static org.testng.Assert.fail;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;

import Reusable.ReusableLibrary;

// Commonly used selenium action were implemented in Reusable Library

public class BSAPage extends ReusableLibrary {

	// few question handled using age. So created a special varioable age
	public int age = 0;

	String ref1 = "<<Replaceme>>";

	By acceptCookiesEl = By.xpath("//button[contains(text(),'OK with analytics cookies')]");

	// Templates for finding web elements
	String labelIDTemplate = "//label[@id='label-" + ref1 + "']";
	String inputValueTemplate = "//input[@value='" + ref1 + "']";
	String inputIdTemplate = "//input[@id='" + ref1 + "']";
	String dentalCheckupEl = "//*[contains(text(),'" + ref1
			+ "')]/preceding-sibling::span[@class=\"form-hint-list-item__indicator\"]";

	/*
	 * Method will navigate the user to the input url.
	 * 
	 * @Parameter: url
	 */
	public void navigateUserTo(String url) {
		OpenUrl(url);
	}

	/*
	 * This method will accept the cookie pop up in the initial page of NHSBSA
	 * 
	 */
	public void acceptCookies() {
		ClickElement(acceptCookiesEl, "Accept Cookies");
	}

	/*
	 * Using this evaluation will be started
	 */
	public void startQuestion() {
		ClickElement(GetXpath(inputIdTemplate, "next-button"), "Start");
	}

	/*
	 * Method is will helps in navigating to next questions
	 */
	public void clickNext() {
		ClickElement(GetXpath(inputValueTemplate, "Next"), "Next");
	}

	/*
	 * Method can help in choosing the country
	 * 
	 * @parameter: countryName
	 */
	public void chooseCountry(String countryName) {
		ClickElement(GetXpath(labelIDTemplate, countryName.toLowerCase()), countryName);
		clickNext();
	}

	/*
	 * Using this method dob will be set
	 * 
	 * @Parameter: Date of birth in day-month-year format
	 */
	public void setDob(String dob) {

		String[] dateofbirth = dob.split("-");
		EnterText(GetXpath(inputIdTemplate, "dob-day"), dateofbirth[0]);
		EnterText(GetXpath(inputIdTemplate, "dob-month"), dateofbirth[1]);
		EnterText(GetXpath(inputIdTemplate, "dob-year"), dateofbirth[2]);

		age = calculateAge(dob, LocalDate.now());
		clickNext();
		System.out.println(age);

	}

	/*
	 * Age will be calculated from the dob
	 * 
	 * @Parameter: DOB and today's date
	 */
	public int calculateAge(String bDate, LocalDate todayDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
		LocalDate localDate = LocalDate.parse(bDate, formatter);

		return Period.between(localDate, todayDate).getYears();
	}

	/*
	 * All the question will be answered using this method
	 */
	public void answerAllQuestions() {

		if (age <= 18) {
			ClickElement(GetXpath(labelIDTemplate, "no"), "Are you in full-time education? -No");
			clickNext();
		}

		ClickElement(GetXpath(labelIDTemplate, "no"), "Do you live with a partner? -No");
		clickNext();
		ClickElement(GetXpath(labelIDTemplate, "no"), "Do you claim any benefits or tax credits?- No");
		clickNext();
		if (age <= 60) {
			ClickElement(GetXpath(labelIDTemplate, "no"),
					"Are you pregnant or have you given birth in the last 12 months? - No");
			clickNext();
		}
		ClickElement(GetXpath(labelIDTemplate, "no"),
				"Do you have an injury or illness caused by serving in the armed forces? -No");
		clickNext();
		if (age < 60) {
			ClickElement(GetXpath(labelIDTemplate, "no"), "Do you have diabetes? -No");
			clickNext();
			ClickElement(GetXpath(labelIDTemplate, "no"), "Do you have glaucoma? - No");
			clickNext();
		}
		ClickElement(GetXpath(labelIDTemplate, "no"), "Do you live permanently in a care home?- No");
		clickNext();
		ClickElement(GetXpath(labelIDTemplate, "no"),
				"Do you have more than £16,000 in savings, investments or property? - No");
		clickNext();

	}

	/*
	 * Presence of free dental will be validated using this method
	 * 
	 * @Paramter: flag (we need to have free dental option or not)
	 */

	public void checkFreeDental(boolean flag) {

		int occurance = ElementCount(GetXpath(dentalCheckupEl, "NHS dental check-ups"));
		if ((flag ^ occurance >= 1)) {
			fail("Problem in NHS Dental checkup");
		}

	}
}
