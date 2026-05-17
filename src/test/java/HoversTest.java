import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class HoversTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
    }

    @Test
    public void checkProfiles() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        Actions actions = new Actions(driver);
        List<WebElement> profiles = driver.findElements(By.cssSelector(".figure"));
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < profiles.size(); i++) {
            profiles = driver.findElements(By.cssSelector(".figure"));
            WebElement profile = profiles.get(i);
            actions.moveToElement(profile).perform();
            WebElement userName = profile.findElement(By.cssSelector(".figcaption h5"));
            String expectedUserName = "name: user" + (i + 1);
            softAssert.assertEquals(userName.getText(), expectedUserName, "The username is incorrect.");
            WebElement profileLink = profile.findElement(By.cssSelector(".figcaption a"));
            profileLink.click();
            softAssert.assertFalse(driver.getPageSource().contains("Not Found"), "404 page was opened.");
            driver.navigate().back();
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
