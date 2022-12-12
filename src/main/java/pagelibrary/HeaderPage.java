package pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import testRunner.RedesignedHDTestRunner;

public class HeaderPage extends RedesignedHDTestRunner{
	private WebDriver driver = null;
	private Wait<WebDriver> wait = null;
	
	public HeaderPage(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(this.driver, this);
	}
	
	//WebElements
	@FindBy(id = "downloadRpc")
	public WebElement btnDownloadHD;
	
	@FindBy(id = "imgSource")
	public WebElement lnkLoggedInUserName;
	
	@FindBy(id = "logout")
	public WebElement lnkLogOut;
	
	@FindBy(id = "account")
	public WebElement lnkMyAccount;
	
	
	//methods
	public void logout() {
		lnkLoggedInUserName.click();
		wait.until(ExpectedConditions.visibilityOf(lnkLogOut));
		lnkLogOut.click();
	}
	
	public void clickMyAccount() {
		lnkLoggedInUserName.click();
		wait.until(ExpectedConditions.visibilityOf(lnkMyAccount));
		lnkMyAccount.click();
	}

}
