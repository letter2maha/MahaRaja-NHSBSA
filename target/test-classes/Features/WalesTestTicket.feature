
@nhsbsa
Feature: Eligibility Checker
Background: 
Given I navigate to Eligibilty Checker start page
When I click start Now
Then I should access the Eligibilty cheker Questions

Scenario Outline: Wales Test Ticket - Free Dental check -"<dob>" (Age Range 18 to 25 and Above 60)
Given I am a person from "Wales"
And My date of birth is "<dob>"
When I put my circumstances into the checker tool
Then I should get free dental check
 Examples: 
|dob|
|29-10-2003|
|29-10-1997|
|10-10-1961|



Scenario Outline: Wales Test Ticket - No Free Dental check "<dob>" (Age Range 25 to 60)
Given I am a person from "Wales"
And My date of birth is "<dob>"
When I put my circumstances into the checker tool
Then I should not get free dental check
 Examples: 
|dob|
|10-10-1994|
|10-10-1962|