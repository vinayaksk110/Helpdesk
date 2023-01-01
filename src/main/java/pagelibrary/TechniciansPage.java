package pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import testRunner.RedesignedHDTestRunner;

public class TechniciansPage extends RedesignedHDTestRunner{
	private WebDriver driver = null;
	private Wait<WebDriver> wait = null;
	
	public TechniciansPage(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(id="createTeamUserEmptyDiv")
	public WebElement BtnEmptyAddTechnician;
	
	@FindBy(id="invite_back_btn")
	public WebElement BtnInviteTechnicianBack;
	
	@FindBy(id="emailIds")
	public WebElement TxtBoxInviteTechnicianEmailAddress;
	
	@FindBy(id="addUserTeams")
	public WebElement DropdowninviteTechnicianGroups;
	
	@FindBy(id="invite_TeamUsers")
	public WebElement BtnInviteTechnicianInviteUser;
	
	@FindBy(id="cancelInvite")
	public WebElement BtnInviteTechnicianCancel;
	
	@FindBy(id="hd_invite_as_admin")
	public WebElement ChkboxInviteTechnicianMakeAsAdmin;
	
	@FindBy(id="twofaCheckAdd")
	public WebElement ChkboxInviteTechnicianEnable2FA;
	
	private void clickAddTechnicianCentreButton() {
		BtnEmptyAddTechnician.click();
	}
	
	private void ClickBackButtonFromInviteUsers() {
		BtnInviteTechnicianBack.click();
	}
	
	private void enteremailAddressInvitesers() {
		TxtBoxInviteTechnicianEmailAddress.sendKeys("vinayak");
	}
	
	
	
	

}
