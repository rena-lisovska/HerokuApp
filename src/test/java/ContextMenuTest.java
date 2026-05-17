import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class ContextMenuTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
    }

    @Test
    public void checkAlertMessage() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/context_menu");
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.id("hot-spot"));
        actions.contextClick(element).perform();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String actualText = alert.getText();
        softAssert.assertEquals(actualText, "You selected a context menu", "The alert text is missing or different from what was expected.");
        alert.accept();
        boolean alertClosed = wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        softAssert.assertTrue(alertClosed, "Alert is still present");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
