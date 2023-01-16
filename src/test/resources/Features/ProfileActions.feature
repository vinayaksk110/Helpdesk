Feature: Update profile details

Background: User is already Logged in
Given user is already logged in to the applicaton
 
 	@ProfileTest
  Scenario: Update firstname from profile
  	Given user is on home page and navigates to profiles
    When user updates the first name and clicks on Save
    Then user details shoule be saved