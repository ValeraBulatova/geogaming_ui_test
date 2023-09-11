package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static ChromeOptions options;
    public static WebDriver DRIVER;

    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        DRIVER = new ChromeDriver(options);
        DRIVER.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        DRIVER.manage().window().setSize(new Dimension(1270, 1100));
    }
}
