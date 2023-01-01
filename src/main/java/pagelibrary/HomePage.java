package pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import testRunner.RedesignedHDTestRunner;

public class HomePage extends RedesignedHDTestRunner{
	private WebDriver driver = null;
	private Wait<WebDriver> wait = null;
	
	
	public void click(WebElement webElement) {
		webElement.click();
	}
	
	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(this.driver, this);
	}

	public void uploadFile(WebElement element, String uploadPathOfFile) {
		element.sendKeys(uploadPathOfFile);	
	}

	/**
	 * This method allows the user to logout of the account 
	 * @param webElement: This is the element which has to be clicked to access the logout button in the top slider.
	 */

}
