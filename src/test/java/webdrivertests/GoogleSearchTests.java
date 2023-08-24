package webdrivertests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class GoogleSearchTests {
    public enum BrowserTypes {
        FIREFOX,
        FIREFOX_HEADLESS,
        CHROME,
        CHROME_HEADLESS,
        EDGE,
        EDGE_HEADLESS
    }
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void classSetup() {
        //Setup browser

        driver = startBrowser(BrowserTypes.CHROME);

        //Wait
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));

         //Navigate to Google.com
        driver.get("https://google.com");

        //Reject cookies
        WebElement rejectButton = driver.findElement(By.xpath("//button[@id='W0wltc']"));
        rejectButton.click();

    }

    @AfterAll
    public static void classTearDown(){
        //Close
        driver.close();
    }

    @BeforeEach
    public void testSetup() {
        //Navigate to Google.com
        driver.get("https://google.com");
    }

    @Test
    public void resultFound_when_searchTermProvided_telerikAcademy() {
        String searchTerm = "Telerik Academy Alpha";
        String searchResult = "IT Career Start in 6 Months - Telerik Academy Alpha";
        //Type text in search box
        WebElement searchField = driver.findElement(By.xpath("//textarea[@type='search']"));
        searchField.sendKeys(searchTerm);

        //Click search button
        WebElement searchButton = driver.findElement(By.xpath("(//input[@type='submit'][@name='btnK'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();

        //Assert Result found
        WebElement firstResult = driver.findElement(By.xpath("(//a/h3)[1]"));
        Assertions.assertTrue(firstResult.getText().contains(searchResult), "Search not found");

    }

    private static WebDriver startBrowser(BrowserTypes browserType) {
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
