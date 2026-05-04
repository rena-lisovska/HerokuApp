import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DropdownTest {
    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу http://the-internet.herokuapp.com/dropdown
    2. Нажать на поле с дропдаун
    2. Выбрать 1-й элемент дропдауна
    3. Выбрать 2-й элемент дропдауна

    Посткондишн: закрыть браузер

    Ожидаемый результат:
    - после выбора 1-го дропдауна система фиксирует выбор "Option 1"
    - после выбора 2-го дропдауна система фиксирует выбор "Option 2"
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
    public void checkDropdownChange() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("http://the-internet.herokuapp.com/dropdown");
        driver.findElement(By.id("dropdown")).click();
        WebElement element = driver.findElement(By.id("dropdown"));
        Select select = new Select(element);
        select.selectByValue("1");
        String choiceFirst = select.getFirstSelectedOption().getText();
        softAssert.assertEquals(choiceFirst, "Option 1");
        driver.findElement(By.id("dropdown")).click();
        select.selectByValue("2");
        String choiceSecond = select.getFirstSelectedOption().getText();
        softAssert.assertEquals(choiceSecond, "Option 2");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
