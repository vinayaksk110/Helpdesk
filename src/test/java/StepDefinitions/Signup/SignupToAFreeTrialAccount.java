package StepDefinitions.Signup;


import pagelibrary.LoginPage;
import testRunner.RedesignedHDTestRunner;

public class SignupToAFreeTrialAccount extends RedesignedHDTestRunner {
	
	LoginPage loginPage = null;
	
	public SignupToAFreeTrialAccount() throws Exception {
		loginPage = new LoginPage(driver, wait);
		
	}

}
