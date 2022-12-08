package testbase;


public class HelpDeskConstants {
	
	// Browser names
	public static final String FIREFOX = "firefox";
	public static final String CHROME = "chrome";
	
	public static final String URL_LOGIN ="https://www.idrive.com/idrive/login/loginForm";
	
	// Path of the Credentials file
	public static final String CREDENTIALS_PATH = "src\\test\\resources\\sheetsAPIclient_secret.json";
	
	// The path of file to be uploaded
	private static final String UPLOAD_PATH_OF_FILE1 ="C:\\Users\\";
	private static final String UPLOAD_PATH_OF_FILE2 = "\\OneDrive\\Desktop\\Finland_Serbia_Locator.png";
	private static final String loggedInUser = System.getProperty("user.name");
	public static final String PATH_OF_FILETOBEUPLOADED = UPLOAD_PATH_OF_FILE1.concat(loggedInUser.concat(UPLOAD_PATH_OF_FILE2));
	
	// The worksheet ID where test result report will be written.
	// Account : automationreports@idrive.com / WorkSheet Name : IDrive_TestResults / IDrive_AccountsForTesting
	public static final String WORKSHEETID_TESTRESULTS	= "17SchpMdJmHHxRVsNCjmnAYUzlMxd-FXLT2UalZ_g7oA";
	public static final String WORKSHEETID_ACCOUNTS = "1Ts-6EDON7rgUWLnEAwb0ZhYwc8NVPYrvWMbvTxfwQSI";
	
	// Below are the sheetranges to be used for fetching username details.
	public static final String SHEETRANGE_LOGIN = "Login!A2:D";
	public static final String SHEETRANGE_CANCELACCOUNTSINBULK = "CancelRPCAccountsInBulk!A2:E";
	public static final String SHEETRANGE_CANCELACCOUNT = "CancelAccount!A2:D";
	public static final String SHEETRANGE_FORGOTPASSWORD = "ForgotPassword!A2:E";
	public static final String SHEETRANGE_DISABLEAUTORENEWAL = "DisableAutoRenewal!A2:E";
	public static final String SHEETRANGE_UPGRADE = "Upgrade!A2:E";
	//public static final String SHEETRANGE_PROFILECASES = "ProfileCases!A3:E";
	public static final String SHEETRANGE_SIGNUP = "Signup!A2:M";
	public static final String SHEETRANGE_PROFILE = "Profile!A2:J";
	public static final String SHEETRANGE_USERMANAGEMENT = "UserManagement!A2:L";

	// not specifying the sheet name below for test results sheet as, it will be dynamically fetched at the 
	//time or writing the test results
	public static final String SHEETRANGE_TESTRESULTS = "!A2:E";
	public static final String WINDOWS_EMAILABLE_REPORT = null;
}
