package utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;

import ReportsOnGoogleSheets.ReportsOnGoogleSheets.sheetFunctions.SpreadsheetSnippets;
import ReportsOnGoogleSheets.ReportsOnGoogleSheets.testBase.GoogleCredentials;
import testbase.HelpDeskConstants;

public class GoogleDriveExcelUtility {
	SpreadsheetSnippets snippets = null;
	ValueRange valueRange = new ValueRange();
	public ValueRange result = null;
	UserCredentials userData = new UserCredentials();

	public GoogleDriveExcelUtility() {
	}

	/**
	 * The constructor allows creation of service request for sheets present in the
	 * google drive.
	 * 
	 * @param secretsFile : this is the path of the authorization file.
	 */
	public GoogleDriveExcelUtility(String secretsFile) {
		CreateServiceRequest(secretsFile);
	}

	/**
	 * The constructor allows creation of service request for sheets present in the
	 * google drive and also to create a excel sheet that can be used to write the
	 * test results to.
	 * 
	 * @param secretsFile : This is the authentication file for google apis
	 * @param date        : This is the date on which the test is executed and the
	 *                    sheet that contains test results in the workbook
	 * @throws Exception : Thrown by below layer
	 */
	public GoogleDriveExcelUtility(String secretsFile, String workbookID) {
		CreateServiceRequest(secretsFile);

	}

	/**
	 * The method allows to create the service required for interacting with google
	 * sheets
	 * 
	 * @param sercretsFile: Authentication information to create a valid service.
	 * 
	 */
	private void CreateServiceRequest(String sercretsFile) {
		try {
			//System.out.println("Secrets file location: " + sercretsFile);
			// System.out.println("spreadSheetID : "+Constants.spreadSheetID);
			GoogleCredentials.createSheetsServiceRequest(sercretsFile);
			snippets = new SpreadsheetSnippets(GoogleCredentials.sheetService, GoogleCredentials.driveService);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method allows to write the test result status to the designated excel
	 * workbook. Note: the sheet where data has to be written has to be in the first
	 * index position in the workbook.
	 */
	public void writeTestStatus(String testName, String testResult, String testResultComment) {

		System.out.println("===============");
		System.out.println("Test name: " + testName);
		System.out.println("Test result: " + testResult);
		System.out.println("Test result comment: " + testResultComment);
		System.out.println("===============");

		// Prepare data for writing.
		List<Object> values = new ArrayList<Object>();
		values.add(testName);
		values.add(testResult);
		values.add(testResultComment);

		List<List<Object>> listOfLists = new ArrayList<List<Object>>();
		listOfLists.add(values);

		try {
			DateNTime dateNTime = new DateNTime();
			String sheetname = dateNTime.printCurrentDate();
			sheetname.concat(HelpDeskConstants.SHEETRANGE_TESTRESULTS);
			snippets.appendValues(HelpDeskConstants.WORKSHEETID_TESTRESULTS, sheetname, "USER_ENTERED", listOfLists);
			System.out.println(" ==> Added Test results to Excel <==");
			System.out.println(testName + ":" + testResult + ":");
		} catch (IOException e) {
			try {
				snippets.appendValues(HelpDeskConstants.WORKSHEETID_TESTRESULTS, HelpDeskConstants.SHEETRANGE_TESTRESULTS,
						"USER_ENTERED", listOfLists);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println(" ==> Added Test results to Excel <==");
			System.out.println(testName + ":" + testResult + ":");
			e.printStackTrace();
		}
	}

	/**
	 * Method allows to write the test result status to the designated excel
	 * workbook. Note: the sheet where data has to be written has to be in the first
	 * index position in the workbook.
	 * 
	 * @param worksheetID       : The id of the workbook where the data is to be
	 *                          written.
	 * @param workSheetRange    : the name of the sheet and the range in which data
	 *                          has to be written.
	 * @param moduleName        : the module being tested, it is also the class
	 *                          name.
	 * @param moduleTestResult  : result of the test to be written.
	 * @param testResultComment : is the final message got in the application based
	 *                          on which moduleTestResult is determined.
	 */
	public void writeTestStatus(String worksheetID, String workSheetRange, String moduleName, String moduleTestResult,
			String testResultComment) {

		// Prepare data for writing.
		List<Object> values = new ArrayList<Object>();
		values.add(moduleName);
		values.add(moduleTestResult);
		values.add(testResultComment);

		List<List<Object>> listOfLists = new ArrayList<List<Object>>();
		listOfLists.add(values);

		try {
			snippets.appendValues(worksheetID, workSheetRange, "USER_ENTERED", listOfLists);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param spreadshetId : This is the excel sheet in google drive from which
	 *                     ValueRange has to be found
	 * @param range        : This is the range that has to be found from the excel
	 *                     sheet
	 * @return : once the range has been found in the spreadsheeId, this returned
	 * @throws IOException
	 */
	public ValueRange readValueRange(String spreadshetId, String range) throws IOException {
		result = snippets.getValues(spreadshetId, range);
		return result;
	}

	/**
	 * 
	 * @param row    : The row where the string is present
	 * @param column : The column where the string is present.
	 * @return : the string found at the intersection of row and column in lower case.
	 * @throws IOException
	 */
	public String readCellData(int row, int column, ValueRange excelRange) throws IOException {

		List<List<Object>> values = excelRange.getValues();
		String cellData = values.get(row).get(column).toString();
		return cellData.toLowerCase();
	}

	/**
	 * This method allows to get the number of rows in an spreadsheet and the
	 * suggested range
	 * 
	 * @param spreadsheetID : The spreadsheet from which the row count is to be
	 *                      found
	 * @param range         : the range of cells from which row count is to be found
	 * @return : the count of active rows in the range of the spreadsheet
	 * @throws IOException
	 */
	public int getRowCount(String spreadsheetID, String range) throws IOException {

		int rowCount = snippets.rowCount(spreadsheetID, range);
		return rowCount;
	}

	/**
	 * Allows to create new sheets with in an existing spreadsheet
	 * 
	 * @param spreadsheetID : The spreadsheet in which the sheet is to be created
	 * @param sheetName     : This is the name of the sheet that is to be created
	 *                      within the spreadsheet
	 * @throws Exception
	 */
	public void addSheetToSpreadSheet(String spreadsheetID, String sheetName) {

		try {
			Sheets.Spreadsheets.Get request = GoogleCredentials.sheetService.spreadsheets().get(spreadsheetID);
			Spreadsheet ss = request.execute();
			// Check if the sheet doesnot exists and add the sheet along with heading.
			for (Sheet sheet : ss.getSheets()) {
				if (!sheet.getProperties().getTitle().contains(sheetName)) {
					snippets.addSheetToSpreadSheet(spreadsheetID, sheetName);
					//updateHeading();
				}
			}
		} catch (GoogleJsonResponseException e) {
			System.out.println(">>>Skipped adding sheet to the workbook as the name of the sheet already exists<<<<");
		} catch (IOException e) {
			System.out.println("Unable to add sheet to workbook");
			e.printStackTrace();
		}

	}

	/**
	 * This method allows us to get the user credentials from the excel sheet based
	 * on the type of account name being passed.
	 * 
	 * @param typeOfAccount : This is the type of account for which the credentials
	 *                      has to be got.
	 * @return : Returns an object of class UserCredentials
	 * @throws Exception : that indicates user is trying get a account for which the
	 *                   account type doesnot exists.
	 */
	public UserCredentials getAccountCredentials(String typeOfAccount) throws Exception {
		ValueRange range = new ValueRange();
		UserCredentials credentials = new UserCredentials();
		if (typeOfAccount.equalsIgnoreCase("con")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_LOGIN);
			credentials.emailID = readCellData(0, 2, range);
			credentials.password = readCellData(0, 3, range);
			System.out.println("\n \nGot credentials for CONSUMER account");
		} else if (typeOfAccount.equalsIgnoreCase("soho")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_LOGIN);
			credentials.emailID = readCellData(1, 2, range);
			credentials.password = readCellData(1, 3, range);
			System.out.println("\n \nGot credentials for SOHO account");
		} else if (typeOfAccount.equalsIgnoreCase("team")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_LOGIN);
			credentials.emailID = readCellData(2, 2, range);
			credentials.password = readCellData(2, 3, range);
			System.out.println("\n \nGot credentials for TEAM account");
		} else if (typeOfAccount.equalsIgnoreCase("enterprise")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_LOGIN);
			credentials.emailID = readCellData(3, 2, range);
			credentials.password = readCellData(3, 3, range);
			System.out.println("\n \nGot credentials for ENTERPRISE account");
		} else if (typeOfAccount.equalsIgnoreCase("free")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_LOGIN);
			credentials.emailID = readCellData(4, 2, range);
			credentials.password = readCellData(4, 3, range);
		} else if (typeOfAccount.equalsIgnoreCase("forgotpassword")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_FORGOTPASSWORD);
			credentials.emailID = readCellData(0, 0, range);
			System.out.println("\n \nGot credentials for FORGOT PASSWORD account");
		} else if (typeOfAccount.equalsIgnoreCase("cancelaccount")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_CANCELACCOUNT);
			credentials.emailID = readCellData(0, 2, range);
		}
		// Cancel account in bulk is not considered as the logic for getting cancelled
		// accounts is different
		/*
		 * else if (typeOfAccount.equalsIgnoreCase("cancelaccountsinbulk")) { range =
		 * readValueRange(IDriveConstants.WORKSHEETID_ACCOUNTS,
		 * IDriveConstants.SHEETRANGE_CANCELACCOUNTSINBULK); credentials.emailID =
		 * readCellData(0, 2, range); }
		 */
		else if (typeOfAccount.equalsIgnoreCase("disableautorenewal")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_DISABLEAUTORENEWAL);
			credentials.emailID = readCellData(0, 2, range);

		} else if (typeOfAccount.equalsIgnoreCase("upgrade")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_UPGRADE);
			credentials.emailID = readCellData(0, 2, range);
		} else if (typeOfAccount.equalsIgnoreCase("profilecases")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_PROFILE);
			credentials.emailID = readCellData(0, 0, range);
			credentials.password = readCellData(0, 1, range);
		} else if (typeOfAccount.equalsIgnoreCase("accountsection")) {
			range = readValueRange(HelpDeskConstants.WORKSHEETID_ACCOUNTS, HelpDeskConstants.SHEETRANGE_PROFILE);
			credentials.emailID = readCellData(1, 2, range);
			credentials.password = readCellData(1, 3, range);
		} else {
			throw new Exception("You are trying to get a username category that doesnot exists");
		}

		return credentials;
	}

	/**
	 * This method download the updated excel test results to the local system. 
	 * @throws Exception : when the file is not present in the location where its being copied to
	 */
	public void downloadTestResultsReport() throws Exception {
		
		snippets.downloadSpreadsheetInWorkspace(HelpDeskConstants.WORKSHEETID_TESTRESULTS, "D:\\data\\RPCTestResults.xls");
		System.out.println("Successfully downloaded the report");
		
		// Copy the file to the jenkins workspace, so that the latest file will be
		// available for reporting.
		File src = new File("D:\\data\\RPCTestResults.xls");
		File dest = new File(
				"C:\\WINDOWS\\system32\\config\\systemprofile\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\RemotePC Automation Tests\\RPC Full Website Test");
		FileUtils.copyFileToDirectory(src, dest);
		System.out.println("Copied file to default workspace");
	}

	/**
	 * This method allows a row to be made bold
	 * @param spreadsheetId : Is the current active spreadsheet 
	 * @param sheetId : Is the active sheet inside the workbook
	 * @param startRowIndex : The starting row to be made bold
	 * @param endRowIndex : The ending row to be made bold
	 * @throws Exception : throws general exception
	 */
	public void makeTheRowBold(String spreadsheetId, int sheetId, int startRowIndex, int endRowIndex) throws Exception {

		snippets.makeRowBold(spreadsheetId, sheetId, startRowIndex, endRowIndex);

	}
	
	/**
	 * This method updates the heading before writing the test results.
	 */
	private void updateHeading() {
		writeTestStatus("Test Case", "Test Result", "Test Result Comment");
	}

	/**
	 * This method returns the sheetID, an indexed integer value, to identify the sheet name with in a spreadsheet.
	 * @param spreadsheetId : This is the spreadsheet identifier
	 * @param sheetName : The sheetname for which the value should be returned based on name
	 * @return : An indexed integer, that identifies the sheet in the spreadsheet
	 * @throws Exception : Throws exception when the sheet name is not found.
	 */
	public int getSheetID(String spreadsheetId, String sheetName) throws Exception {
		return snippets.getSheetId(spreadsheetId, sheetName);
	}
	
	public UserCredentials getCompleteSheetData(String spreadsheetID, String sheetRange, String dataIdentifier) throws Exception  {
		
		dataIdentifier = dataIdentifier.toLowerCase();
		// Get the entire sheet values in one go.
		List<List<Object>> sheetValues;
		try {
			sheetValues = snippets.getSheetValues(spreadsheetID, sheetRange);
		} catch (IOException e) {
			System.out.println("Got exception while getting sheet values");
			throw new Exception();
		}

		// Get the row and column count for further processing.
		int totalNoOfColumns = sheetValues.get(0).size();

		// Extract the list of column headers and store them for further comparison.
		List<String> columnHeaders = new ArrayList<String>();
		List<String> rowValues = new ArrayList();

		for (Object columnHeader : sheetValues.get(0)) {
			columnHeaders.add(columnHeader.toString());
		}

		// Since the column header is extracted, now remove the first row.
		sheetValues.remove(0);

		// Based on the data identifier, extract the rowvalues and store them for
		// further processing
		for (List<Object> listobj : sheetValues) {
			if (listobj.get(1).toString().toLowerCase().equals(dataIdentifier)) {
				for (Object obj : listobj) {
					rowValues.add(obj.toString());
				}
				break;
			}
		}
		
		//Based on the column name, get the values of this column.
		for (int i = 0; i < totalNoOfColumns; i++) {
			String columnHeading = columnHeaders.get(i).toLowerCase().toString();
			if (columnHeading.equals ("username"))
				userData.emailID = rowValues.get(i).toString();
			else if(columnHeading.equals("password"))
				userData.password = rowValues.get(i).toString();
			else if(columnHeading.equals("first name"))
				userData.firstName = rowValues.get(i).toString();
			else if(columnHeading.equals("last name"))
				userData.lastName = rowValues.get(i).toString();
			else if(columnHeading.equals("phone"))
				userData.phoneNo = rowValues.get(i).toString();
			else if(columnHeading.equals("cc"))
				userData.ccNo = rowValues.get(i).toString();
			else if(columnHeading.equals("mm"))
				userData.mm = rowValues.get(i).toString();
			else if(columnHeading.equals("yy"))
				userData.yy = rowValues.get(i).toString();
			else if(columnHeading.equals("cvv"))
				userData.cvv = rowValues.get(i).toString();
			else if(columnHeading.equals("billing address"))
				userData.billingAddress = rowValues.get(i).toString();
			else if(columnHeading.equals("zip code"))
				userData.zipcode = rowValues.get(i).toString();
		}
		
		return userData;
	}
	
	/**
	 * This method allows user to download the excel sheet from the drive
	 * @param spreadsheetId : This is the spreadsheeId that is to be downloaded
	 * @param destinationPath : The location where the file will be stored
	 * @throws Exception : Thrown when the file is not available for download
	 */
	public void downloadFile(String spreadsheetId, String destinationPath) throws Exception {
		try {
			snippets.downloadSpreadsheetInWorkspace(spreadsheetId, destinationPath);
		} catch (IOException e) {
			throw e;
		}
	}

}
