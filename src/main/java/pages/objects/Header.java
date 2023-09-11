package pages.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BaseSelection;

import java.util.List;

public class Header extends BaseSelection {

    @FindBy(css = ".spb-ism-e3__item")
    List<WebElement> category;
    @FindBy(css = ".spb-sport-picker-e3")
    WebElement sportsLiveHeader;
    @FindBy(css = ".spb-ism-e3__menu-items")
    WebElement casinoHeader;

    public Header(WebDriver driver) {
        super(driver);
    }
}
