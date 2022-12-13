
Feature: Login to New HD application
 
 @LoginTest @SmokeTest @RegressionTest
  Scenario: Login to the new HD application as Admin
  	Given user is on login page
    When user enters the admin username and password and clicks Login
    Then user should be logged in and home page should be displayed
