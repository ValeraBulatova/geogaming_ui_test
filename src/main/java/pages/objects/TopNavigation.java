package pages.objects;

import control.Control;
import driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;
import pages.base.BaseSelection;
import properties.SmokeProperties;

import java.util.List;

import static org.testng.Assert.*;

public class TopNavigation extends BaseSelection {

    private static final Logger LOGGER = LogManager.getLogger();

    @FindBy(css = ".spb-gateway")
    private List<WebElement> gateWay;
    @FindBy(css = ".spb-logo")
    private WebElement logo;
    @FindBy(css = "[data-test-locator=\"BUTTON_JOIN_NOW\"]")
    private WebElement joinButton;

    public TopNavigation(WebDriver driver) {
        super(driver);
    }

    public void clickJoinButton() {
        Control.waitElementIsVisible(logo);
        joinButton.click();
        LOGGER.info("Button 'JOIN' was clicked");
    }

    public void selectGateWay(String module) {
        Control.waitElementIsVisible(logo);
        selectElementFromList(gateWay, module).click();
        LOGGER.info(String.format("Gateway %s was selected", module));
        Control.waitElementIsVisible(logo);
    }

    public void assertContent(String module) {
        Header header = new Header(Driver.DRIVER);
        switch (module) {
            case "Sports" -> {
                assertTrue(header.sportsLiveHeader.isDisplayed());
                assertTrue(new SportsPage(Driver.DRIVER).sportTable.isDisplayed());
            }
            case "Live" -> {
                assertTrue(header.sportsLiveHeader.isDisplayed());
                assertTrue(new LivePage(Driver.DRIVER).liveTable.isDisplayed());
            }
            case "Casino" -> {
                CasinoPage casinoPage = new CasinoPage(Driver.DRIVER);
                Control.waitElementIsVisible(header.casinoHeader);
                assertTrue(header.casinoHeader.isDisplayed());
                assertEquals(casinoPage.casinoBlocks.size(), 6);
                Control.waitAssertionListValues(casinoPage.blockNames, SmokeProperties.CASINO_BLOCK_NAMES.values);
            }
            case "Live Casino" -> {
                LiveCasinoPage liveCasino = new LiveCasinoPage(Driver.DRIVER);
                Control.waitElementIsVisible(header.casinoHeader);
                assertTrue(header.casinoHeader.isDisplayed());
                Control.waitAssertionListValues(liveCasino.blockNames, SmokeProperties.LIVE_CASINO_BLOCK_NAMES.values);
            }
            case "BetGames" -> {
                BetGamesPage betGames = new BetGamesPage(Driver.DRIVER);
                Control.waitAssertionListValues(betGames.generalGameNames, SmokeProperties.BETGAMES_SPORTS_NAME.values);
            }
            case "Virtual Sports" -> {
                VirtualSportsPage virtualSports = new VirtualSportsPage(Driver.DRIVER);
                Control.waitAssertionListValues(virtualSports.generalGameNames, SmokeProperties.VIRTUAL_SPORTS_GAME_NAME.values);
            }
            case "Promos" -> {
                PromosPage promos = new PromosPage(Driver.DRIVER);
                Control.waitAssertionListValues(promos.sections, SmokeProperties.PROMO_SECTIONS.values);
                Control.waitAssertionListValues(promos.noPromoMessage, SmokeProperties.NO_PROMO_MESSAGE.values);
            }
        }
    }
}
