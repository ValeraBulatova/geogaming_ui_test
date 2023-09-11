package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BaseSelection;

import java.util.List;

public class PromosPage extends BaseSelection {

    @FindBy(css = ".spb-promotions-tabs__tab")
    public List<WebElement> sections;
    @FindBy(css = ".spb-promotion--not-available")
    public List<WebElement> noPromoMessage;
    public PromosPage(WebDriver driver) {
        super(driver);
    }
}
