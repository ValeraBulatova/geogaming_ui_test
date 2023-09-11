package control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.awaitility.Awaitility.*;

public class Control {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void waitElementIsVisible(WebElement element) {
        given().ignoreExceptions().await().atMost(10, TimeUnit.SECONDS)
                .until(element::isDisplayed);
    }

    public static void waitAssertionListValues(List<WebElement> actualValues, List<String> requiredValues) {
        LOGGER.info("Actual values before loading are: " + getValuesFromList(actualValues));
        given().ignoreExceptions().await().atMost(10, TimeUnit.SECONDS)
                .untilAsserted(() -> Assert.assertEquals(getValuesFromList(actualValues), requiredValues));
        LOGGER.info("Actual values after loading are: " + getValuesFromList(actualValues));
    }

    public static List<String> getValuesFromList(List<WebElement> wes) {
        return wes.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
