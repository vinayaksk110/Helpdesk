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
	@FindBy(id = "fname")
	public WebElement TxtBoxFirstName;
	
	@FindBy(id = "downloadRpc")
	public WebElement TxtBoxLastName;
	
	@FindBy(id = "downloadRpc")
	public WebElement btnDownloadHD;

}
