package pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import testRunner.RedesignedHDTestRunner;

public class HomePage extends RedesignedHDTestRunner{
	private WebDriver driver = null;
	private Wait<WebDriver> wait = null;
	
	
	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath = "//label[ contains(text(),'Access customer device')]")
	public WebElement radioButtonAccessCustDevice;



	/**
	 * This method allows the user to logout of the account 
	 * @param webElement: This is the element which has to be clicked to access the logout button in the top slider.
	 */

}
