package StepDefinitions.ProfileActions;

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
	public void user_is_on_home_page_and_navigates_to_profiles() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("user updates the first name and clicks on Save")
	public void user_updates_the_first_name_and_clicks_on_save() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("user details shoule be saved")
	public void user_details_shoule_be_saved() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


}
