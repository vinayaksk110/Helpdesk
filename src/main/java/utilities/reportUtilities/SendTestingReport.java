package utilities.reportUtilities;

import org.apache.commons.mail.EmailException;
import org.testng.annotations.Test;

import testbase.HelpDeskConstants;

public class SendTestingReport extends utilities.EmailUtilities {

	utilities.EmailUtilities emailUtility = new utilities.EmailUtilities();
	utilities.GoogleDriveExcelUtility excelUtility = new utilities.GoogleDriveExcelUtility(HelpDeskConstants.CREDENTIALS_PATH);
	String testResult = null;
	String testResultComment = null;
	@Test
	public void sendEmail() {
		String testName = SendTestingReport.class.getSimpleName();
		try {
			emailUtility.sendHTMLAttachmentAsEmail();
			testResult = "Pass";
			testResultComment = "Email sent successfully";
			System.out.println("================================================");
			System.out.println(testName+" executed successfully.");
			System.out.println("================================================");
		}  catch (EmailException e) {
			testResult = "Fail";
			testResultComment = "Email was not sent successfully due to exception";
			e.printStackTrace();
		}
		finally {
			excelUtility.writeTestStatus(testName, testResult, testResultComment);
		}

	}
}
