package webdrivertests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class GoogleSearchTests extends BaseClass {

    private static WebDriver driver;
    private static WebDriverWait wait;



    @BeforeAll
    public static void googleClassSetup() {

        //Setup browser
        driver = startBrowser(BrowserTypes.EDGE_HEADLESS);

        //Wait
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));

         //Navigate to Google.com
        driver.get("https://google.com");

        //Reject cookies
        WebElement rejectButton = driver.findElement(By.xpath("//button[@id='W0wltc']"));
        rejectButton.click();

    }

    @AfterAll
    public static void googleClassTearDown(){
        //Close
        driver.close();
    }


    @Test
    public void googleResultFound_when_searchTermProvided_telerikAcademy() {
        String searchTerm = "Telerik Academy Alpha";
        String searchResult = "IT Career Start in 6 Months - Telerik Academy Alpha";

        //Type text in search box
        WebElement searchField = driver.findElement(By.xpath("//textarea[@type='search']"));
        searchField.sendKeys(searchTerm);
        searchField.click();

        //Click search button
        WebElement searchButton1 = driver.findElement(By.xpath("(//input[@type='submit'][@name='btnK'])[1]"));
        WebElement searchButton2 = driver.findElement(By.xpath("(//input[@type='submit'][@name='btnK'])[2]"));
        wait.until(ExpectedConditions.visibilityOf(searchButton1));
        searchButton1.click();

        //Assert Result found
        WebElement firstResult = driver.findElement(By.xpath("(//a/h3)[1]"));
        wait.until(ExpectedConditions.visibilityOf(firstResult));
        Assertions.assertTrue(firstResult.getText().contains(searchResult), "Search not found");

    }



}
