
Feature: signup to New HD application
 
 @SignupTest @SmokeTest @RegressionTest
  Scenario: Signup to a free trial account
  	Given user is on login page and navigates to signup page
    When user enters the details and clicks on signup
    Then user should be signed up and home page should be displayed