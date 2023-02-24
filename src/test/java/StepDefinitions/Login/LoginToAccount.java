package StepDefinitions.Login;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagelibrary.HeaderPage;
import pagelibrary.HomePage;
import pagelibrary.LoginPage;
import testRunner.RedesignedHDTestRunner;
import utilities.UserCredentials;

public class LoginToAccount extends RedesignedHDTestRunner {

	LoginPage loginPage = null;
	HomePage homePage = null;
	HeaderPage headerPage = null;
	UserCredentials userCredentials = null;

	public LoginToAccount() throws Exception {
		loginPage = new LoginPage(driver, wait);
		homePage = new HomePage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
		userCredentials = new UserCredentials();
	}

	@Given("user is on login page")
	public void NavigateToLoginPage() throws InterruptedException {
		//Connecting to DB
		try {
			userCredentials = sqlData.Getdata("Login", "LoginToAccount");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			driver.get(repository.getProperty("LoginURL"));
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@When("user enters the admin username and password and clicks Login")
	public void EnterCredsAndClickLogin() {
		try {
			wait.until(ExpectedConditions.visibilityOf(loginPage.txtbxEmail));
			loginPage.fillUserCredentials(userCredentials.emailID, userCredentials.password);
			loginPage.clickSignIn();
		} catch (Exception e) {
			e.printStackTrace();
			headerPage.logout();
			Assert.assertTrue(false);
		}
	}

	@Then("user should be logged in and home page should be displayed")
	public void homePageShouldBeDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(headerPage.btnDownloadHD));
			String HomePageTitle =driver.getTitle();
			headerPage.logout();
			Assert.assertEquals("RemotePC™ HelpDesk - Service Queue", HomePageTitle);
			System.out.println("Login to a account test passed. Download installer was displayed");
		}catch (Exception e){
			headerPage.logout();
			Assert.assertTrue(false);
		}
	}

}
