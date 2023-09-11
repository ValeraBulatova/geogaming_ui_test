package pages.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BaseSelection {

    protected WebDriver driver;

    private static final Logger LOGGER = LogManager.getLogger();

    public BaseSelection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement selectElementFromList(List<WebElement> elements, String name) {
        for (WebElement el : elements) {
            if (el.getText().equals(name)) return el;
        }
        LOGGER.error(String.format("Element with text '%s' was not found", name));
        return null;
    }

    public void selectOptionWithRetry(Select select, Runnable selection, int count) {
        try {
            select.getWrappedElement().click();
            selection.run();
        } catch (UnsupportedOperationException e) {
            if (count < 3) {
                count++;
                selectOptionWithRetry(select, selection, count);
            }
        }
    }
}
