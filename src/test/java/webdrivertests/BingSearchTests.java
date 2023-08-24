package webdrivertests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BingSearchTests extends BaseClass {

    private static WebDriver driver;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    @BeforeAll
    public static void bingClassSetup() {
        //Setup browser
        driver = startBrowser(BrowserTypes.EDGE_HEADLESS);

        //Navigate to Google.com
        driver.get("https://bing.com");

    }

    @AfterAll
    public static void bingClassTearDown(){
        //Close
        driver.close();
    }


    @Test
    public void bingResultFound_when_searchTermProvided_telerikAcademy() {
        String searchTerm = "Telerik Academy Alpha";
        String searchResult = "IT Career Start in 6 Months";
        String result = "IT Career Start in 6 Months - Telerik Academy Alpha";

        //Type text in search box
        WebElement searchField = driver.findElement(By.xpath("//input[@id='sb_form_q']"));
        searchField.sendKeys(searchTerm);

        //Click search button
        WebElement searchButton = driver.findElement(By.cssSelector("#search_icon.search.icon.tooltip"));
        searchButton.click();

        //Assert Result found
        WebElement firstResult = driver.findElement(By.xpath("(//h2/a)[1]"));

        wait.until(ExpectedConditions.visibilityOf(firstResult));
        Assertions.assertTrue(firstResult.getText().contains(searchTerm), "Search not found");
        Assertions.assertTrue(firstResult.getText().contains(searchResult), "Search not found");

       // Assertions.assertEquals(result,firstResult.getText(), "Search not found");

    }


}
