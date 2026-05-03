import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class NotificationMessageTest {

    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу https://the-internet.herokuapp.com/notification_message_rendered
    2. Нажать на ссылку Click here и дождаться появления нотификации

    Посткондишн: закрыть браузер

    Ожидаемый результат:
    - отображается текст "Action successful" при успешном сценарии
    - отображается текст "Action unsuccessful, please try again" при неуспешном сценарии
    */

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkTextNotificationSuccessful() {
        SoftAssert softAssert = new SoftAssert();
        String expectedTextNotificationSuccessful = "Action successful";
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        driver.findElement(By.linkText("Click here")).click();
        String actualTextNotificationSuccessful = driver.findElement(By.id("flash")).getText();
        softAssert.assertEquals(actualTextNotificationSuccessful, expectedTextNotificationSuccessful);
        softAssert.assertAll();
    }

    @Test
    public void checkTextNotificationUnsuccessful() {
        SoftAssert softAssert = new SoftAssert();
        String expectedTextNotificationUnsuccessful = "Action unsuccessful, please try again";
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        driver.findElement(By.linkText("Click here")).click();
        String actualTextNotificationUnsuccessful = driver.findElement(By.id("flash")).getText();
        softAssert.assertEquals(actualTextNotificationUnsuccessful, expectedTextNotificationUnsuccessful);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
