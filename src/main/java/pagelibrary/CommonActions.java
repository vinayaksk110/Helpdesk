package pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import testRunner.RedesignedHDTestRunner;

public class CommonActions extends RedesignedHDTestRunner {
	private WebDriver driver = null;
	private Wait<WebDriver> wait = null;
	
	
	public CommonActions(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath = "//*[@class='notification-wrap']")
	public WebElement successMsg;
	

}
