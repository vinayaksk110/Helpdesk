package pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import testRunner.RedesignedHDTestRunner;

public class ProfilesPage extends RedesignedHDTestRunner {
	private WebDriver driver = null;
	private Wait<WebDriver> wait = null;
	
	public ProfilesPage(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(this.driver, this);
	}
	
	//WebElements
	@FindBy(xpath = "//div[@class='inp-row']/div/label[contains(text(),'First Name')]//following-sibling::input")
	public WebElement txtBoxFirstName;
	
	@FindBy(xpath = "//div[@class='inp-row']/div/label[contains(text(),'Last Name')]//following-sibling::input")
	public WebElement txtBoxLastName;
	
	@FindBy(xpath = "//div[@class='inp-row']/div/label[contains(text(),'Phone Number')]//following-sibling::input")
	public WebElement txtBoxPhoneNumber;
	
	
	@FindBy(xpath = "//button[@class='btn btn-primary' and contains (text(),'Save')]")
	public WebElement buttonSave;
	
	public void updateFirstName(String fname) {
		txtBoxFirstName.clear();
		txtBoxFirstName.sendKeys(fname);
	}
	
	public void updateLastName(String lname) {
		txtBoxLastName.clear();
		txtBoxLastName.sendKeys(lname);
	}

}
