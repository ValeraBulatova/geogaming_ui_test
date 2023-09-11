package pages.commonElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BaseSelection;

import java.util.List;

public class CasinoGeneral extends BaseSelection {

    @FindBy(css = ".spb-games-category__name")
    public List<WebElement> blockNames;
    public CasinoGeneral(WebDriver driver) {
        super(driver);
    }
}
