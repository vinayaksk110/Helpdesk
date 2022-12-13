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
	
	

}
