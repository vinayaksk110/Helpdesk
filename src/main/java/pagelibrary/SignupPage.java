package pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import testRunner.RedesignedHDTestRunner;

public class SignupPage extends RedesignedHDTestRunner{
	private WebDriver driver = null;
	private Wait<WebDriver> wait = null;
	
	/**
	 * 
	 * @param driver
	 * @param wait
	 */
	public SignupPage(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(this.driver, this);		
	}
	
	@FindBy(id ="3000_fr")
	public WebElement rdoBtnFreeTrial;
	
	@FindBy(id ="3001_pd")
	public WebElement rdoBtn1Technician;
	
	@FindBy(id ="3002_pd")
	public WebElement rdoBtn5Technician;
	
	@FindBy(id ="3000_pd")
	public WebElement rdoBtnUnlimitedTechnician;
	
	@FindBy(id ="fname")
	public WebElement txtBoxFirstName;
	
	@FindBy(id ="lname")
	public WebElement txtBoxLastName;
	
	@FindBy(id ="email")
	public WebElement txtBoxEmail;
	
	@FindBy(id ="pwd")
	public WebElement txtBoxPassword;
	
	@FindBy(id ="countryCode")
	public WebElement dropDownCountryCode;
	
	@FindBy(id ="phone")
	public WebElement txtBoxPhone;
	
	@FindBy(xpath ="//iframe[contains(@title,'Secure card payment')]")
	public WebElement iFrameCCDetails;
	
	@FindBy(xpath ="//input[@name = 'cardnumber']")
	public WebElement txtBoxCCNo;
	
	@FindBy(xpath ="//input[@name = 'exp-date']")
	public WebElement txtBoxMMYY;
	
	@FindBy(xpath ="//input[@name = 'cvc']")
	public WebElement txtBoxCvv;
	
	@FindBy(id ="bill")
	public WebElement txtBoxBillingAddress;
	
	@FindBy(id ="terms_check")
	public WebElement chkBoxTermsAndConditions;
	
	@FindBy(id ="submit-signup")
	public WebElement btnSignup;
	
	public void enterFirstName(String firstName) {
		txtBoxFirstName.sendKeys(firstName);
		System.out.println("Entered the first name: "+firstName);
	}
	
	public void enterLastName(String lastName) {
		txtBoxLastName.sendKeys(lastName);
		System.out.println("Entered the last name: "+lastName);
	}
	
	public void clickFreeTrialRadioButton() {
		rdoBtnFreeTrial.click();
		System.out.println("Clicked on Free trial radio button");
	}
	
	public void enterEmailaddress(String email) {
		txtBoxEmail.sendKeys(email);
		System.out.println("Entered the email: "+email);
	}
	
	public void enterPassword(String password) {
		txtBoxPassword.sendKeys(password);
		System.out.println("Entered the password: "+password);
	}
	
	public void enterPhoneNumber(String phone) {
		txtBoxPassword.sendKeys(phone);
		System.out.println("Entered the phone number: "+phone);
	}
	
	public void clickTermsAndConditions() {
		chkBoxTermsAndConditions.click();
		System.out.println("Clicked on Accept terms and conditions");
	}
	
	
	public void clickSignup() {
		btnSignup.click();
		System.out.println("Clicked on signup button");
	}
	

}
