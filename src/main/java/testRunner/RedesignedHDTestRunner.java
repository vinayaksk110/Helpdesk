package testRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import testbase.HelpDeskConstants;
import utilities.DateNTime;
import utilities.GoogleDriveExcelUtility;
import utilities.SQLTesting;
import utilities.reportUtilities.ReportHelper;

@CucumberOptions(
		features = "src\\test\\resources\\Features",
		glue = { "StepDefinitions" },
		plugin = {"pretty" , "json:target/cucumber-reports/cucumber-reports.json","html:target/cucumber-reports/cucumber-reports.html"},
		tags = "@LoginTest",
		monochrome = true,
		dryRun = false)

public class RedesignedHDTestRunner extends AbstractTestNGCucumberTests {
	
	public static Properties repository = new Properties();
	public File f = null;
	public InputStream fis = null;
	public static WebDriver driver = null;
	public static Wait<WebDriver> wait = null;
	public DateNTime dateNTime = new DateNTime();
	public String filePath = null;
	String os = null;
	String firefoxDriverPath = null;
	String chromeDriverPath = null;
	boolean excelStatus = false;
	boolean headless = false;

	protected GoogleDriveExcelUtility excelUtility = null;
	SQLTesting sqlData;
	protected ReportHelper reportHelper = null;

	// For writing to excel sheet.
	protected String testName = null;
	protected String testResult = "FAIL";
	protected String testResultComment = "Test has not yet started";

	protected SoftAssert softAssertion = new SoftAssert();

	@BeforeClass(alwaysRun = true)
	public void initializeEnvironment(@Optional("yes") String excelFileStatus, @Optional("chrome") String browser,
			@Optional("no") String headlessMode) throws FileNotFoundException, IOException {
//		System.out.println("\n \nStarting the test on TestCase: " + testName);
		try {
			initializeTestingEnvironment(excelFileStatus, browser, headlessMode);
		} catch (FileNotFoundException fnfe) {
			System.out.println("Properties file could not be found");
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Path for file is not found or incorrect.");
			ioe.printStackTrace();
		} catch (TimeoutException toe) {
			System.out.println("Wait creation failed");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterClass(alwaysRun = true)
	public void closeEnvironment() {
		// Write the test results and then shut down the environment.
		try {
			takeSnapShot();
//			excelUtility.writeTestStatus(testName, testResult, testResultComment);
			driver.quit();
//			excelUtility.downloadTestResultsReport();
		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
	}
	
	/**
	 * Generates the cucumberReport in target folder
	 */
	@AfterSuite(alwaysRun = true)
	public void generateHTMLReports() {
		reportHelper = new ReportHelper();
		ReportHelper.generateCucumberReport();
	}

	/**
	 * This method loads the project config file
	 * 
	 * @throws IOException, FileNotFoundException
	 */
	private void loadPropertiesFile() throws IOException, FileNotFoundException {
		repository = new Properties();
		f = new File(HelpDeskConstants.CONFIG);
		fis = new FileInputStream(f);

		if (fis != null) {
			repository.load(fis);
			System.out.println("Loaded properties file successfully");
		} else {
			throw new FileNotFoundException("Property file Config.propertes not found in the resource path");
		}
	}

	/**
	 * This method initialized the project by loading the properties files, creates
	 * the required browser and creates the wait element of the project.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void initializeTestingEnvironment(String excelFileStatus, String browser, String headless) throws Exception {
		System.out.println("==========Initializing Test Environment==============");
		System.out.println(excelFileStatus + " : " + browser + " : " + headless);
		if (excelFileStatus.equalsIgnoreCase("yes")) {
			this.excelStatus = true;
		}

		if (headless.equalsIgnoreCase("yes")) {
			this.headless = true;
		}

		try {
			// Proceed with loading properties and browser driver creation only if,
			// the reports can be written to an excel sheet.
			Assert.assertEquals(this.excelStatus, true);

			// Load the project properties file and create the required browser
			loadPropertiesFile();

			// Create the browser driver
			createBrowser(browser, this.headless);

			// Create the object of excel utility to write the results back to sheets in
			// google drive.
			sqlData = new SQLTesting();

//			excelUtility = new GoogleDriveExcelUtility(HelpDeskConstants.CREDENTIALS_PATH);
//			excelUtility.addSheetToSpreadSheet(HelpDeskConstants.WORKSHEETID_TESTRESULTS, dateNTime.printCurrentDate());

			// Create the extent report object for writing the report
//			setupExtentRepoter();

			System.out.println("============================");
			System.out.println("Project will run in:");
			System.out.println("Brower: " + browser.toUpperCase());
			System.out.println("Headless = " + this.headless);
			System.out.println("============================");

		} catch (AssertionError ae) {
			System.out.println("Aborting!! : Your excel file is not ready. ");
			throw ae;

			// ae.getMessage();
			// return;`
		} catch (NullPointerException npe) {
			System.out.println("Was unable to create the object for excel drive uitility");
			npe.getMessage();
		}
	}

//	private String setupExtentRepoter() {
//		String reportConfigPath = repository.getProperty("extentConfigFile");
//		if (reportConfigPath != null)
//			return reportConfigPath;
//		else
//			throw new RuntimeException(
//					"Report Config Path not specified in the Configuration.properties file for the Key : setupExtentRepoter");
//
//	}

	/**
	 * based on the browser required to be run, Create the driver instance.
	 * 
	 * @param browser
	 */
	private void createBrowser(String browser, boolean headless) throws Exception {

		// Find the operating system.
		os = System.getProperty("os.name");
		System.out.println("Environment Operating System: " + os);
		// Based on the browser type create the required browser for the project
		if (browser.equalsIgnoreCase(HelpDeskConstants.FIREFOX)) {
			if (!headless) {
				System.out.println("Creating Environment: Firefox driver");
				WebDriverManager.firefoxdriver().setup();
				// Enable Logging preferences
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
				System.out.println("Logging preferences set");
				driver = new FirefoxDriver(firefoxOptions);
				System.out.println("Firefox driver created");
				driver.manage().window().maximize();
			} else {
				System.out.println("Creating Environment: Firefox driver in headless mode");
				WebDriverManager.firefoxdriver().setup();
				// Enable Logging preferences
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
				System.out.println("Logging preferences set");
				firefoxOptions.addArguments("--headless");
				driver = new FirefoxDriver(firefoxOptions);
				System.out.println("Firefox driver created");
				driver.manage().window().maximize();
			}
		} else if (browser.equalsIgnoreCase(HelpDeskConstants.CHROME)) {
			// check if the browser has to be loaded in headless mode
			if (headless) {
				System.out.println("Creating Environment: Chrome driver in headless mode");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--window-size=1366x768");
				System.out.println("View port set :");
				chromeOptions.addArguments("--headless");
				WebDriverManager.chromedriver().setup();
				System.out.println("Chrome driver created");
				driver = new ChromeDriver(chromeOptions);
				driver.manage().window().maximize();
			} else {
				System.out.println("Creating Environment: Chrome driver");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--window-size=1366x768");
				System.out.println("View port set : else");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
				System.out.println("Chrome driver created");
				driver.manage().window().maximize();
			}
		}

		// Once the web driver element is successfully created,
		// Create the fluent wait element using the driver.
		createFluenttWait(wait);
	}

	/**
	 * This method creates the project specific wait.
	 * 
	 * @param wait
	 */
	public void createFluenttWait(Wait<WebDriver> wait) throws Exception {
		if (wait == null) {
			int timeOut = 30;
			this.wait = new FluentWait<WebDriver>(driver)
					// Timeout time is set to 60
					.withTimeout(Duration.ofSeconds(HelpDeskConstants.FLUENT_TIMEOUT))
					// polling interval
					.pollingEvery(Duration.ofMillis(100))
					// ignore the exception
					.ignoring(NoSuchElementException.class, ElementNotInteractableException.class);
			System.out.println("Created wait with browser timeout of " + HelpDeskConstants.FLUENT_TIMEOUT + " seconds");
		}
	}

	public boolean waitForElement(String element, String elementType) {
		boolean waitResult = false;

		if (elementType.equalsIgnoreCase("xpath")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
				waitResult = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return waitResult;
			}
		} else if (elementType.equalsIgnoreCase("id")) {
			if (elementType.equalsIgnoreCase("id")) {
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
					waitResult = true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return waitResult;
				}
			}

		} else if (elementType.equalsIgnoreCase("name")) {
			if (elementType.equalsIgnoreCase("name")) {
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
					waitResult = true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return waitResult;
				}
			}

		}
		return waitResult;
	}
	
	
	public void takeSnapShot() throws Exception {
		try {
			Date date = new Date() ;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss") ;
			// Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			// Move image file to new destination
//			File DestFile = new File(System.getProperty("user.home")+"\\git\\Helpdesk\\Screenshots"+dateFormat.format(date)+".png");
//			File DestFile = new File("C:\\Users\\Vinayak\\git\\Helpdesk\\Screenshots\\scr."+Math.random()+".png");
			File DestFile = new File("C:\\Users\\Vinayak\\git\\Helpdesk\\Screenshots\\"+dateFormat.format(date)+".png");
			// Copy file at destination
			FileUtils.copyFile(SrcFile,DestFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Screenshot method failed due to following reason"+e.getMessage());
		}
	}
	


}
