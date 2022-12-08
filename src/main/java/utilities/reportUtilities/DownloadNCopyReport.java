// This class allows user to download the report generated from the google drive
// and copy it to the jenkins_home folder so that, once jenkins completes the tests, 
// it can attach the report and send it as email.

package utilities.reportUtilities;

import org.testng.annotations.Test;

import testbase.HelpDeskConstants;

public class DownloadNCopyReport {

	utilities.GoogleDriveExcelUtility excelUtility = new utilities.GoogleDriveExcelUtility(HelpDeskConstants.CREDENTIALS_PATH,
			HelpDeskConstants.WORKSHEETID_TESTRESULTS);

	@Test
	public void startDownloadNCopyOfReport() {
		try {
			excelUtility.downloadTestResultsReport();
		} catch (Exception e) {
			System.out.println("Exception while downloading the file and attaching to Jenkins_Home");
			e.printStackTrace();
		}
	}
}
