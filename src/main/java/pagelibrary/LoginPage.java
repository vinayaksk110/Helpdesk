package pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.testng.asserts.SoftAssert;

import testRunner.RedesignedHDTestRunner;

public class LoginPage extends RedesignedHDTestRunner{
	private WebDriver driver = null;
	private Wait<WebDriver> wait = null;
	
    SoftAssert softAssert = new SoftAssert();
	
	@FindBy(id = "email")
	public WebElement txtbxEmail;
	
	@FindBy(id = "password")
	public WebElement txtbxPassword;
	
	@FindBy(id ="loginButton")
	public WebElement btnSignIn;
	
	@FindBy(linkText = "Forgot password?")
	public WebElement lnktxtForgotPassword;
	
	/**
	 * This is the constructor that takes the web driver from test base and sets it to the Login page functions
	 * @param driver
	 */
	public LoginPage(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(this.driver, this);		
	}
	
//	public LoginPage(WebDriver driver) {
//		this.driver = driver;
//		PageFactory.initElements(this.driver, this);		
//	}
	
	
	/**
	 * Allows to enter the users email address into the email text box
	 * @param email : Value that is to be entered
	 */
	private void enterEmail(String email) {
		txtbxEmail.sendKeys(email);
		System.out.println("Entered the email address: "+email);
	}
	
	/**
	 * Allows to enter the users password into the password text box
	 * @param password : password to be entered 
	 */
	private void enterPassword(String password) {
		txtbxPassword.sendKeys(password);
		System.out.println("Entered the password: "+password);
	}
	
	/**
	 * Click on the signin button
	 */
	public void clickSignIn() {
		btnSignIn.click();
		System.out.println("Clicked on Login button");
	}
	
	/**
	 * Click on the forgot password link
	 */
	public void clickForgotPassword() {
		lnktxtForgotPassword.click();
	}
	
	/**
	 * This method allows to fill both the userName and password on the login page
	 * @param userName : is value to be filled in the userName text box.
	 * @param password : is the value to be filled in the password text box.
	 */
	public void fillUserCredentials(String userName, String password) {
		enterEmail(userName);
		enterPassword(password);
	}

}
