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

public class LoginToAccount extends RedesignedHDTestRunner {

	LoginPage loginPage = null;
	HomePage homePage = null;
	HeaderPage headerPage = null;

	public LoginToAccount() throws Exception {
		loginPage = new LoginPage(driver, wait);
		homePage = new HomePage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
	}

	@Given("user is on login page")
	public void NavigateToLoginPage() throws InterruptedException {
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
			loginPage.fillUserCredentials("vinayak.kumbar+team@idrive.com", "test12");
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
			headerPage.logout();
			System.out.println("Login to a account test passed. Download installer was displayed");
		}catch (Exception e){
			headerPage.logout();
			Assert.assertTrue(false);
		}
	}

}
