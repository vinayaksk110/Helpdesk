package StepDefinitions.ProfileActions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagelibrary.CommonActions;
import pagelibrary.HeaderPage;
import pagelibrary.HomePage;
import pagelibrary.LoginPage;
import pagelibrary.ProfilesPage;
import testRunner.RedesignedHDTestRunner;
import testbase.TestBase;

public class UpdateFirstName extends RedesignedHDTestRunner {
	
	LoginPage loginPage = null;
	HomePage homePage = null;
	ProfilesPage profilePage = null;
	HeaderPage headerPage = null;
	CommonActions commonAction = null;
	
	public UpdateFirstName() throws Exception {
		loginPage = new LoginPage(driver, wait);
		homePage = new HomePage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
		profilePage = new ProfilesPage(driver, wait);
		commonAction = new CommonActions(driver, wait);
	}
	
	@Given("user is already logged in to the applicaton")
	public void userLoginToApplication() {
		try {
			driver.get(repository.getProperty("LoginURL"));
			wait.until(ExpectedConditions.visibilityOf(loginPage.txtbxEmail));
			loginPage.fillUserCredentials("vinayak.kumbar+helpdesk1601@idrive.com", "test12");
			loginPage.clickSignIn();
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Given("user is on home page and navigates to profiles")
	public void UserNavigatesToProfilePage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(homePage.radioButtonAccessCustDevice));
			headerPage.clickMyAccount();
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("user updates the first name and clicks on Save")
	public void UserUpdatingFirstName() {
		try {
			profilePage.updateFirstName(testName);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	    
	}
	@Then("user details shoule be saved")
	public void SuccessMessage() {
	    wait.until(ExpectedConditions.visibilityOf(commonAction.successMsg));
	}


}
