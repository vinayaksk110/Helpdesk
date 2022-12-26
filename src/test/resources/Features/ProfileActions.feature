Feature: Update profile details
 
  Scenario: Update firstname from profile
  	Given user is on home page and navigates to profiles
    When user updates the first name and clicks on Save
    Then user details shoule be saved