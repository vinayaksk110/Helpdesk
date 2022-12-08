package Hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import testbase.HelpDeskConstants;

public class helpdeskHooks {

	public static WebDriver driver;
	
	
	@BeforeClass
	public synchronized static WebDriver createBrowser(String browser, boolean headless) throws Exception {

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
				driver = new ChromeDriver(chromeOptions);
			} else {
				System.out.println("Creating Environment: Chrome driver");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.addArguments("--window-size=1366x768");
				System.out.println("View port set : else");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
			}
		}
		driver.manage().window().maximize();
		return driver;
	}


	@AfterClass
	public void afterClass(Scenario scenario) {
		System.out.println("After all method");
		// Write the test results and then shut down the environment.
//		excelUtility.writeTestStatus(testName, testResult, testResultComment);
		driver.quit();
	}

}
