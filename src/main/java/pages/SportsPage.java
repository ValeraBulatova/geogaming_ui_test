package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BaseSelection;

public class SportsPage extends BaseSelection {

    @FindBy(css = ".spb-table")
    public WebElement sportTable;
    public SportsPage(WebDriver driver) {
        super(driver);
    }
}
