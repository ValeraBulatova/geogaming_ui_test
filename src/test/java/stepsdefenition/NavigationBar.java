package stepsdefenition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.objects.TopNavigation;
import driver.Driver;

import static org.testng.Assert.assertTrue;

public class NavigationBar {

    TopNavigation navigation;

    public NavigationBar() {
        navigation = new TopNavigation(Driver.DRIVER);
    }

    @Given("Start page of application is opened")
    public void testGiven() {
        assertTrue(Driver.DRIVER.getCurrentUrl().contains("https://en.greatodds.com/"));
    }

    @When("user selects {string}")
    public void testWhen(String module) {
        navigation.selectGateWay(module);
    }

    @Then("{string} is open")
    public void testThen(String module) {
        navigation.assertContent(module);
    }
}
