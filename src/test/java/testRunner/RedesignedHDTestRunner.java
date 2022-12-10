package testRunner;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import testbase.TestBase;


@CucumberOptions(
		features = "src\\test\\resources\\Features",
		glue = { "StepDefinitions" },
		plugin = {"pretty"},
		monochrome = true,
		dryRun = false)

public class RedesignedHDTestRunner extends AbstractTestNGCucumberTests {
	
	TestBase testbase = new TestBase();
	
	@BeforeSuite
	public void initializeEnvironment(@Optional("yes") String excelFileStatus, @Optional("chrome") String browser,
			@Optional("no") String headlessMode) throws FileNotFoundException, IOException {
		try {
			testbase.initializeTestingEnvironment(excelFileStatus, browser, headlessMode);
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
	
	@AfterSuite(alwaysRun = true)
	public void quit() throws IOException, InterruptedException {
		testbase.closeEnvironment();
	}
	
	

}

