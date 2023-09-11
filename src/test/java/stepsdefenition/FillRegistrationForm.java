package stepsdefenition;

import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.objects.RegistrationForm;
import pages.objects.TopNavigation;

public class FillRegistrationForm {

    TopNavigation navigation;
    RegistrationForm registration;

    public FillRegistrationForm() {
        navigation = new TopNavigation(Driver.DRIVER);
        registration = new RegistrationForm(Driver.DRIVER);
    }

    @Given("registration popup is open")
    public void openRegistrationForm() {
        navigation.clickJoinButton();
    }

    @When("user fill registration form")
    public void fillSection() {
        registration.fillAllFieldsInRegistrationForm();
    }

    @And("user removes value from {string}")
    public void removeValueFromField(String field) {
        registration.deleteValueFromField(field);
    }

    @And("click submit button")
    public void submitCreation () {
        registration.submitAccount();
    }

    @And("set date of birth less then 18 years ago")
    public void setBirthdayTomorrow () {
        registration.setYoungAge();
    }

    @Then("error is marked with a {string}")
    public void assertTheMessage(String message) {
        registration.checkErrorMessage(message);
    }

    @Then("Activation account instruction is displayed")
    public void checkActivationForm() {
        registration.checkPopupCreatedAccount();
    }
    @Then("validation message is displayed")
    public void checkValidationOfAge() {
        registration.checkBirthdayErrorMessage();
    }

    @After("@RegistrationFormClosed")
    public void closeRegistrationForm() {
        registration.closeForm();
    }
}
