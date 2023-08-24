package webdrivertests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseClass {

    public enum BrowserTypes {
        FIREFOX,
        FIREFOX_HEADLESS,
        CHROME,
        CHROME_HEADLESS,
        EDGE,
        EDGE_HEADLESS
    }



    public static WebDriver startBrowser(BrowserTypes browserType) {
        switch (browserType) {
            case CHROME:
                return new ChromeDriver();

            case FIREFOX:
                return new FirefoxDriver();

            case EDGE:
                return new EdgeDriver();

            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                return new ChromeDriver(chromeOptions);

            case FIREFOX_HEADLESS:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);

            case EDGE_HEADLESS:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                return new EdgeDriver(edgeOptions);
        }
        return null;
    }


}
