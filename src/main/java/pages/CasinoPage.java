package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.commonElements.CasinoGeneral;

import java.util.List;

public class CasinoPage extends CasinoGeneral {

    @FindBy(css = ".spb-games-category__games-block")
    public List<WebElement> casinoBlocks;

    public CasinoPage(WebDriver driver) {
        super(driver);
    }
}
