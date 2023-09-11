package runners;

import driver.Driver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/java/resources/features",
        glue = "stepsdefenition"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite(alwaysRun = true)
    public void setUpEnvironment() {
        Driver.setUp();
        Driver.DRIVER.get("https://en.greatodds.com/");
    }

    @AfterSuite(alwaysRun = true)
    public void closeTestRun() {
        Driver.DRIVER.close();
        Driver.DRIVER.quit();
    }
}
