package StepDefinitions.Signup;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagelibrary.HeaderPage;
import pagelibrary.LoginPage;
import pagelibrary.SignupPage;
import testRunner.RedesignedHDTestRunner;

public class SignupToAFreeTrialAccount extends RedesignedHDTestRunner {
	
	LoginPage loginPage = null;
	SignupPage signupPage = null;
	HeaderPage headerPage = null;
	
	public SignupToAFreeTrialAccount() throws Exception {
		loginPage = new LoginPage(driver, wait);
		signupPage = new SignupPage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
	}
	
	@Given("user is on login page and navigates to signup page")
	public void NavigateToSignupPage() throws InterruptedException {
		try {
			driver.get(repository.getProperty("LoginURL"));
			loginPage.clickSignUp();
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@When("user enters the details and clicks on signup")
	public void userEntersTheDetails() {
		try {
			wait.until(ExpectedConditions.visibilityOf(signupPage.txtBoxFirstName));
			signupPage.clickFreeTrialRadioButton();
			signupPage.enterFirstName("Vinayak");
			signupPage.enterLastName("Kumbar");
			signupPage.enterEmailaddress("vinayak.kumbar+testHD@idrive.com");
			signupPage.enterPassword("test12");
			signupPage.enterPhoneNumber("9999999999");
			signupPage.clickTermsAndConditions();
			signupPage.clickSignup();
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Then("user should be signed up and home page should be displayed")
	public void waitForHomePage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(headerPage.btnDownloadHD));
			headerPage.logout();
			System.out.println("Login to a account test passed. Download installer was displayed");
		}catch (Exception e){
			headerPage.logout();
			Assert.assertTrue(false);
		}
	}
	

}
