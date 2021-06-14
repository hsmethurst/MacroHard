
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GoogleTest {
    WebDriver browser;

    @BeforeEach
    public void setUp() {
        // Chrome
        System.setProperty("webdriver.chrome.driver", "*****LOCATION OF YOUR WEBDRIVER*****");
        browser = new ChromeDriver();

        // Firefox
        // System.setProperty("webdriver.gecko.driver", "*****LOCATION OF YOUR WEBDRIVER*****");
        // browser = new FirefoxDriver();

        // Safari
        // browser = new SafariDriver();

        browser.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        browser.quit();
    }

    @Test
    public void googlePageLoads() {
        browser.get("https://www.google.com");
        assertEquals("Google", browser.getTitle());
    }

    @Test
    public void googleSearchBoxAppears() {
        browser.get("https://www.google.com");
        WebElement inputBox = browser.findElement(By.name("q"));                    // by name - this works
        // WebElement inputBox = browser.findElement(By.className("gLFyf gsfi"));   // by className - this fails
        // WebElement inputBox = browser.findElement(By.cssSelector(".gLFyf"));     // by cssSelector (aka style) - this works
        // WebElement inputBox = browser.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        // by xpath - this works
        assertTrue(inputBox.isEnabled());
    }

    @Test
    public void googleSearchButtonAppears() {
        browser.get("https://www.google.com");
        WebElement searchButton = browser.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]"));
        assertTrue(searchButton.isEnabled());
    }
    @Test
    public void googleSearchTermAppears() {
        browser.get("https://www.google.com");
        WebElement inputBox = browser.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        inputBox.sendKeys("uvic");
        assertEquals("uvic", inputBox.getAttribute("value"));
    }

    @Test
    public void googleSearchResultsAppear() {
        browser.get("https://www.google.com");
        WebElement inputBox = browser.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        WebElement searchButton = browser.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]"));
        inputBox.sendKeys("uvic");
        searchButton.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.titleIs("uvic - Google Search"));
        assertEquals("uvic - Google Search", browser.getTitle());
    }
}
