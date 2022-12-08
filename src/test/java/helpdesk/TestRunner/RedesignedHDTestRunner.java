package helpdesk.TestRunner;


import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src\\test\\resources\\Features",
		glue = { "StepDefinitions", "Hooks" },
		plugin = {"pretty"},
		monochrome = true,
		dryRun = false)

public class RedesignedHDTestRunner extends AbstractTestNGCucumberTests {
	
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
	

}

