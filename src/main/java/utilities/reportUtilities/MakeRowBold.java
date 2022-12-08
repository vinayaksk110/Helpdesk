package utilities.reportUtilities;

import org.testng.annotations.Test;

import com.google.api.client.util.DateTime;

import testbase.HelpDeskConstants;

public class MakeRowBold {
	utilities.GoogleDriveExcelUtility excelUtility = new utilities.GoogleDriveExcelUtility(HelpDeskConstants.CREDENTIALS_PATH, HelpDeskConstants.WORKSHEETID_TESTRESULTS);
  @Test
  public void makeRowBold() {
	  String sheetName = new utilities.DateNTime().printCurrentDate();
	  System.out.println(sheetName);
	  int sheetId;
	try {
		sheetId = excelUtility.getSheetID(HelpDeskConstants.WORKSHEETID_ACCOUNTS, sheetName);
		System.out.println(sheetId);
		excelUtility.makeTheRowBold(HelpDeskConstants.WORKSHEETID_ACCOUNTS, sheetId, 0, 1);
	} catch (Exception e1) {
		e1.printStackTrace();
	}
  }
}
