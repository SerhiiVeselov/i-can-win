package test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import page.MainPage;

import java.time.Duration;

public class TextbinTest {
    public static WebDriver driver;
    public FluentWait<WebDriver> fluentWait;
    MainPage mainPage;

    @BeforeMethod (alwaysRun = true)
    public void driverSetup() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        driver.manage().window().maximize();
        driver.get("https://textbin.net/");
    }

    @Test (description = "Checking if website is opened", priority = 1)
    public void WebsiteOpenTest() {
        fluentWait.until(ExpectedConditions.visibilityOf(mainPage.getNewPasteTitle()));
        //assertEquals(mainPage.getPageUrl(), "https://textbin.net/");
        assertEquals(driver.getCurrentUrl(), "https://textbin.net/");
        assertEquals(driver.getTitle(), "TextBin");
        //assertEquals(mainPage.getPageTitle(), "TextBin");
    }

    @Test (description = "Creating a new paste", priority = 2)
    public void CreateNewPaste() throws InterruptedException {
        fluentWait.until(ExpectedConditions.visibilityOf(mainPage.getIAgreeBtn()));
        mainPage.closeCookiesPopUp();
        mainPage.pasteTextContent();
        mainPage.enterPasteTitle();
        mainPage.selectPasteExpiration();
        mainPage.clickCreateNewPasteBtn();
        fluentWait.until(ExpectedConditions.visibilityOf(mainPage.getCreatedPasteTitle()));
        fluentWait.until(ExpectedConditions.visibilityOf(mainPage.getCreatedPasteText()));
        assertEquals(mainPage.checkPasteTitle(), "helloweb");
        assertEquals(mainPage.checkPasteText(), "Hello from WebDriver");
    }

    @AfterMethod (alwaysRun = true)
    public void driverQuit() {
        driver.quit();
        driver = null;
    }
}
