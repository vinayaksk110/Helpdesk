package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Hooks.helpdeskHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagelibrary.HomePage;
import pagelibrary.LoginPage;
import testbase.TestBase;

public class LoginToAccount extends TestBase{
	
	private WebDriver driver;
	LoginPage loginPage = null;
	HomePage homePage =null;
//	private Wait<WebDriver> wait= null;
	
	public LoginToAccount() throws Exception {
		this.driver = helpdeskHooks.createBrowser("chrome", false);
		loginPage = new LoginPage(driver, wait);
		homePage = new HomePage(driver, wait);	
	}
	

	@Given("user is on login page")
	public void NavigateToLoginPage() throws InterruptedException {
		driver.get("https://www.remotepc.com/hd-app/login");
	}
	
	@When("user enters the admin username and password and clicks Login")
	public void EnterCredsAndClickLogin() {
		wait.until(ExpectedConditions.visibilityOf(loginPage.txtbxEmail));
		loginPage.fillUserCredentials("vinayak.kumbar+team@idrive.com","test12");
		loginPage.clickSignIn();
	}
	@Then("user should be logged in and home page should be displayed")
	public void homePageShouldBeDisplayed() {
	    homePage.logout();
	}
	
	

}
