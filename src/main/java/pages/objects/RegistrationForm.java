package pages.objects;

import control.Control;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.base.BaseSelection;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.testng.Assert.*;

public class RegistrationForm extends BaseSelection {

    private static final Logger LOGGER      = LogManager.getLogger();
    private static final By DATE_OF_BIRTH   = By.id("registration_dob_day");
    private static final By MONTH_OF_BIRTH  = By.id("registration_dob_month");
    private static final By YEAR_OF_BIRTH   = By.id("registration_dob_year");

    String name;
    @FindBy(id = "registration_firstname")
    private WebElement firstNameField;
    @FindBy(id = "registration_lastname")
    private WebElement surnameField;
    @FindBy(id = "registration_email")
    private WebElement emailField;
    @FindBy(id = "registration_mobile_0")
    private WebElement mobileForm;
    @FindBy(id = "registration_address")
    private WebElement addressField;
    @FindBy(id = "registration_cityname")
    private WebElement cityNameField;
    @FindBy(id = "registration_postcode")
    private WebElement postcodeField;
    @FindBy(id = "registration_login")
    private WebElement usernameField;
    @FindBy(id = "registration_password")
    private WebElement passwordField;
    @FindBy(css = ".registration__error-message")
    private WebElement errorMessage;
    @FindBy(css = ".spb-general-icon-img--cross-circle")
    private WebElement closeButton;
    @FindBy(css = ".registration__button--create")
    private WebElement createAccount;
    @FindBy(css = "label[for=\"registration_acceptance_terms\"]")
    private WebElement checkboxPolicy;
    @FindBy(css = ".registration-popup__container--email-activation")
    private WebElement emailActivationForm;

    public RegistrationForm(WebDriver driver) {
        super(driver);
    }

    public void fillAllFieldsInRegistrationForm() {
        Control.waitElementIsVisible(firstNameField);
//        Fill section 'About you'
        name = RandomStringUtils.randomAlphabetic(5);
        firstNameField.sendKeys(name);
        surnameField.sendKeys(RandomStringUtils.randomAlphabetic(5));
        emailField.sendKeys(RandomStringUtils.randomAlphabetic(5) + "@test.com");
        mobileForm.sendKeys(RandomStringUtils.randomNumeric(8));
        Select dob = new Select(driver.findElement(DATE_OF_BIRTH));
        selectOptionWithRetry(dob,
                () -> dob.selectByIndex((int) Math.floor(Math.random() * (dob.getOptions().size() - 1))),
                0);
        Select month = new Select(driver.findElement(MONTH_OF_BIRTH));
        selectOptionWithRetry(month,
                () -> month.selectByIndex((int) Math.floor(Math.random() * (month.getOptions().size() - 1))),
                0);
        Select year = new Select(driver.findElement(YEAR_OF_BIRTH));
        selectOptionWithRetry(year,
                () -> year.selectByIndex((int) Math.floor(Math.random() * (year.getOptions().size() - 1))),
                0);
//        Fill section 'Location'
        addressField.sendKeys(RandomStringUtils.randomAlphabetic(7));
        cityNameField.sendKeys(RandomStringUtils.randomAlphabetic(7));
        postcodeField.sendKeys(RandomStringUtils.randomAlphabetic(5));
//        Fill section 'Account'
        usernameField.sendKeys(RandomStringUtils.randomAlphabetic(7));
        passwordField.sendKeys(RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(5));
        checkboxPolicy.click();

    }

    public void setYoungAge() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        List<String> birthValues = Arrays.asList(calendar.getTime().toString().split(" "));
        Select dob = new Select(driver.findElement(DATE_OF_BIRTH));
        selectOptionWithRetry(dob,
                () -> dob.selectByValue(birthValues.get(2)),
                0);
        Select month = new Select(driver.findElement(MONTH_OF_BIRTH));
        selectOptionWithRetry(month,
                () -> month.selectByVisibleText(birthValues.get(1)),
                0);
        Select year = new Select(driver.findElement(YEAR_OF_BIRTH));
        int yearOfBirth = Integer.parseInt(birthValues.get(5)) - 18;
        selectOptionWithRetry(year,
                () -> year.selectByValue(String.valueOf(yearOfBirth)),
                0);
        LOGGER.info(String.format("Day of Birth under valid age: %s %s %d",
                birthValues.get(2), birthValues.get(1), yearOfBirth));

    }

    public void deleteValueFromField(String field) {
        switch (field) {
            case "First name" -> {
                firstNameField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            }
            case "Surname" -> {
                surnameField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            }
            case "Email" -> {
                emailField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            }
            case "Number" -> {
                mobileForm.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            }
            case "Address" -> {
                addressField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            }
            case "Town/city" -> {
                cityNameField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            }
            case "Postcode" -> {
                postcodeField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            }
            case "Username" -> {
                usernameField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            }
            case "Password" -> {
                passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            }
            case "Policy" -> {
                checkboxPolicy.click();
            }
        }
        LOGGER.info(String.format("Value of %s was removed", field));
    }

    public void submitAccount() {
        createAccount.click();
        LOGGER.info("Creation account was submitted");
    }

    public void checkErrorMessage(String message) {
        assertEquals(errorMessage.getText().trim(), message);
    }

    public void checkBirthdayErrorMessage() {
        assertEquals(driver.findElement(By.cssSelector(".spb-birthday-date__error-message"))
                        .getText()
                        .trim(),
                "You must be 18 or over to register with Greatodds");
    }

    public void checkPopupCreatedAccount() {
        Control.waitElementIsVisible(emailActivationForm);
        assertTrue(emailActivationForm.isDisplayed());
        assertTrue(driver.findElement(By.cssSelector(".spb-activation__text"))
                .getText()
                .contains(String.format("Thank you for opening an account, %s.", name)));
    }

    public void closeForm() {
        closeButton.click();
        LOGGER.info("Close button was clicked");
    }
}
