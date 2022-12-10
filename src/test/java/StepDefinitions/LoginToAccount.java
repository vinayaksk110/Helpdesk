package StepDefinitions;

import java.time.Duration;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import Hooks.helpdeskHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagelibrary.HomePage;
import pagelibrary.LoginPage;
import testbase.HelpDeskConstants;
import testbase.TestBase;

public class LoginToAccount {

	private static WebDriver driver;
	LoginPage loginPage = null;
	HomePage homePage = null;
	TestBase testBase = null;
	private Wait<WebDriver> wait;

	public LoginToAccount() throws Exception {
		this.driver = helpdeskHooks.createBrowser("chrome", false);
		testBase = new TestBase();
		loginPage = new LoginPage(driver);
//		homePage = new HomePage(driver, wait);
		wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(HelpDeskConstants.FLUENTTIMEOUT))
				// polling interval
				.pollingEvery(Duration.ofMillis(100))
				// ignore the exception
				.ignoring(NoSuchElementException.class, ElementNotInteractableException.class);
		
	}

	@Given("user is on login page")
	public void NavigateToLoginPage() throws InterruptedException {
		driver.get("https://www.remotepc.com/hd-app/login");
	}

	@When("user enters the admin username and password and clicks Login")
	public void EnterCredsAndClickLogin() {
		try {

			wait.until(ExpectedConditions.visibilityOf(loginPage.txtbxEmail));
			loginPage.fillUserCredentials("vinayak.kumbar+team@idrive.com", "test12");
			loginPage.clickSignIn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("user should be logged in and home page should be displayed")
	public void homePageShouldBeDisplayed() {
		homePage.logout();
	}

}
