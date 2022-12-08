package utilities.reportUtilities;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testbase.HelpDeskConstants;

public class AddTestResultHeading {

	utilities.DateNTime dateNTime = new utilities.DateNTime();
	utilities.GoogleDriveExcelUtility excelUtility = new utilities.GoogleDriveExcelUtility(HelpDeskConstants.CREDENTIALS_PATH,
			HelpDeskConstants.WORKSHEETID_TESTRESULTS);

	@Parameters({ "excelFileStatus", "browser", "headlessMode", "jobName", "buildNum"})
	@Test
	public void startAddTestResultsHeading(@Optional("yes") String excelFileStatus, @Optional("chrome") String browser,
			@Optional("no") String headlessMode, @Optional("Jenkins Job Name") String jobName, @Optional("1") String buildNum) {
		
		excelUtility.addSheetToSpreadSheet(HelpDeskConstants.WORKSHEETID_TESTRESULTS, dateNTime.printCurrentDate());
		String currentDateNTime = dateNTime.printCurrentTime("dd/MM/yyyy hh.mm aa");
		//System.out.println("Build No:"+buildNum);
		String bn = "Build# ";
		bn = bn.concat(buildNum);
		System.out.println("Setting up the heading for the job");
		excelUtility.writeTestStatus(jobName, bn, currentDateNTime);
		excelUtility.writeTestStatus("Test Case", "Test Result", "Test Result Comment");

	}
}
