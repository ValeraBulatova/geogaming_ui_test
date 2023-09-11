package pages.commonElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BaseSelection;

import java.util.List;

public class GamesGeneral extends BaseSelection {

    @FindBy(css = ".spb-general-game__name-title")
    public List<WebElement> generalGameNames;

    public GamesGeneral(WebDriver driver) {
        super(driver);
    }
}
