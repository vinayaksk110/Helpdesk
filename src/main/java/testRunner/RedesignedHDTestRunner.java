package testRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;


import io.cucumber.testng.CucumberOptions;

import testbase.HelpDeskTestBase;
import utilities.reportUtilities.ReportHelper;

@CucumberOptions(
		features = "src\\test\\resources\\Features",
		glue = { "StepDefinitions" },
		plugin = {"pretty" , "json:target/cucumber-reports/cucumber-reports.json","html:target/cucumber-reports/cucumber-reports.html"},
		tags = "@ProfileTest",
		monochrome = true,
		dryRun = false)

public class RedesignedHDTestRunner extends HelpDeskTestBase {
	

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
	
	


}
