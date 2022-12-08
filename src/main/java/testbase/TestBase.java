package testbase;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DateNTime;
import utilities.GoogleDriveExcelUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class TestBase {
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
	
	//For writing to excel sheet.
	protected String testName = null;
	protected String testResult = "FAIL";
	protected String testResultComment = "Test has not yet started";
	
	protected SoftAssert softAssertion = new SoftAssert();

	/**
	 * This method loads the project config file
	 * 
	 * @throws IOException,
	 *             FileNotFoundException
	 */
	private void loadPropertiesFile() throws IOException, FileNotFoundException {
//		f = new File(HelpDeskConstants.RPCHDCONFIG);
		fis = new FileInputStream(f);

		if (fis != null) {
			repository.load(fis);
			System.out.println("Loaded properties file successfully");
		} else {
			throw new FileNotFoundException("Property file RemotePCConfig.propertes not found in the resource path");
		}
	}

	/**
	 * This method initialized the project by loading the properties files,
	 * creates the required browser and
	 * creates the wait element of the project.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void initializeTestingEnvironment(String excelFileStatus, String browser, String headless) throws Exception {
		System.out.println("==========Initializing Test Environment==============");
		System.out.println(excelFileStatus+ " : "+browser+" : "+headless);
		if (excelFileStatus.equalsIgnoreCase("yes")) {
			this.excelStatus = true;
		}
		
		if(headless.equalsIgnoreCase("yes")) {
			this.headless = true;
		}
		
		try {
			// Proceed with loading properties and browser driver creation only if,
			// the reports can be written to an excel sheet. 
			Assert.assertEquals(this.excelStatus, true);
		
			// Load the project properties file and create the required browser
			loadPropertiesFile();
			
			// Create the browser driver
//			createBrowser(browser, this.headless);
			
			//Create the object of excel utility to write the results back to sheets in google drive.
			
			excelUtility = new GoogleDriveExcelUtility(HelpDeskConstants.CREDENTIALS_PATH);
			excelUtility.addSheetToSpreadSheet(HelpDeskConstants.WORKSHEETID_TESTRESULTS, dateNTime.printCurrentDate());
			
			//Create the extent report object for writing the report
			setupExtentRepoter();
			
			System.out.println("============================");
			System.out.println("Project will run in:"); 
			System.out.println("Brower: "+browser.toUpperCase());
			System.out.println("Headless = "+this.headless);
			System.out.println("============================");
		
		} catch (AssertionError ae) {
			System.out.println("Aborting!! : Your excel file is not ready. ");
			throw ae;
			
			//ae.getMessage();
			//return;`
		} catch(NullPointerException npe) {
			System.out.println("Was unable to create the object for excel drive uitility");
			npe.getMessage();
		}
	}



	private void setupExtentRepoter() {
		
		
	}

	/**
	 * based on the browser required to be run, Create the driver instance.
	 * 
	 * @param browser
	 */
//	public synchronized static WebDriver createBrowser(String browser, boolean headless) throws Exception {
//
//		if (browser.equalsIgnoreCase(HelpDeskConstants.FIREFOX)) {
//			if (!headless) {
//				System.out.println("Creating Environment: Firefox driver");
//				WebDriverManager.firefoxdriver().setup();
//
//				// Enable Logging preferences
//				FirefoxOptions firefoxOptions = new FirefoxOptions();
//				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
//				System.out.println("Logging preferences set");
//				driver = new FirefoxDriver(firefoxOptions);
//				System.out.println("Firefox driver created");
//			} else {
//				System.out.println("Creating Environment: Firefox driver in headless mode");
//				WebDriverManager.firefoxdriver().setup();
//
//				// Enable Logging preferences
//				FirefoxOptions firefoxOptions = new FirefoxOptions();
//				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
//				System.out.println("Logging preferences set");
//				firefoxOptions.addArguments("--headless");
//				driver = new FirefoxDriver(firefoxOptions);
//				System.out.println("Firefox driver created");
//			}
//		} else if (browser.equalsIgnoreCase(HelpDeskConstants.CHROME)) {
//			// check if the browser has to be loaded in headless mode
//			if (headless) {
//				System.out.println("Creating Environment: Chrome driver in headless mode");
//				System.setProperty("webdriver.chrome.silentOutput", "true");
//				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.addArguments("--window-size=1366x768");
//				System.out.println("View port set :");
//				chromeOptions.addArguments("--headless");
//				WebDriverManager.chromedriver().setup();
//				driver = new ChromeDriver(chromeOptions);
//			} else {
//				System.out.println("Creating Environment: Chrome driver");
//				System.setProperty("webdriver.chrome.silentOutput", "true");
//				ChromeOptions chromeOptions = new ChromeOptions();
////				chromeOptions.addArguments("--window-size=1366x768");
//				System.out.println("View port set : else");
//				WebDriverManager.chromedriver().setup();
//				driver = new ChromeDriver(chromeOptions);
//			}
//		}
//		driver.manage().window().maximize();
//		return driver;
//	}

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
					.withTimeout(Duration.ofSeconds(timeOut))
					// polling interval
					.pollingEvery(Duration.ofMillis(100))
					// ignore the exception
					.ignoring(NoSuchElementException.class, ElementNotInteractableException.class);
			System.out.println("Created wait with browser timeout of "+timeOut+" seconds");
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

		}else if (elementType.equalsIgnoreCase("name")) {
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
	
//	@After
//	public void afterClass(Scenario scenario) {
//		System.out.println("After all method");
//		// Write the test results and then shut down the environment.
////		excelUtility.writeTestStatus(testName, testResult, testResultComment);
//		driver.quit();
//	}
	
}
