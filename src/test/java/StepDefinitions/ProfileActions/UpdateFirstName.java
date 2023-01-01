package StepDefinitions.ProfileActions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagelibrary.HeaderPage;
import pagelibrary.HomePage;
import pagelibrary.LoginPage;
import testRunner.RedesignedHDTestRunner;
import testbase.TestBase;

public class UpdateFirstName extends RedesignedHDTestRunner {
	
	LoginPage loginPage = null;
	HomePage homePage = null;
	TestBase testBase = null;
	HeaderPage headerPage = null;
	
	public UpdateFirstName() throws Exception {
		loginPage = new LoginPage(driver, wait);
		homePage = new HomePage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
	}
	
	@Given("user is on home page and navigates to profiles")
	public void UserNavigatesToHomePage() {
		try {
			driver.get(repository.getProperty("LoginURL"));
			wait.until(ExpectedConditions.visibilityOf(loginPage.txtbxEmail));
			loginPage.fillUserCredentials("vinayak.kumbar+team@idrive.com", "test12");
			loginPage.clickSignIn();
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("user updates the first name and clicks on Save")
	public void UserUpdatingFirstName() {
		try {
			headerPage.clickMyAccount();
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	    
	}
	@Then("user details shoule be saved")
	public void SuccessMessage() {
	    
	}


}
