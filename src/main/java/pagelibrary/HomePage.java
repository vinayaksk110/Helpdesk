package pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class HomePage {
	private WebDriver driver = null;
	private Wait<WebDriver> wait = null;
	
	
	@FindBy(id = "downloadRpc")
	public WebElement btnDownloadHD;
	
	
	@FindBy(id = "imgSource")
	public WebElement lnkLoggedInUserName;
	
	@FindBy(id = "logout")
	public WebElement lnkLogOut;
	
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
	public void logout() {
		lnkLoggedInUserName.click();
		wait.until(ExpectedConditions.visibilityOf(lnkLogOut));
		lnkLogOut.click();
	}

}
