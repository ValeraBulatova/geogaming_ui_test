package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BaseSelection;

public class LivePage extends BaseSelection {

    @FindBy(css = "spb-local-spinner")
    public WebElement liveTable;
    public LivePage(WebDriver driver) {
        super(driver);
    }
}
