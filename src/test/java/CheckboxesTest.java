import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckboxesTest {
    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу http://the-internet.herokuapp.com/checkboxes
    2. Проверить, что первый чекбокс unchecked
    3. Отметить первый чекбокс и проверить, что он checked
    4. Проверить, что второй чекбокс checked
    5. Сделать uncheck, проверить, что второй чекбокс unchecked

    Посткондишн: закрыть браузер

    Ожидаемый результат:
    - первый чекбокс: исходное состояние unchecked, финальное checked
    - второй чекбокс: исходное состояние checked, финальное unchecked
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
    public void checkFirstCheckboxState() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        boolean checkboxFirstUnchecked = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        softAssert.assertFalse(checkboxFirstUnchecked,  "ошибка, по дефолту первый чекбокс в состоянии сhecked");
        driver.findElements(By.cssSelector("[type=checkbox]")).get(0).click();
        boolean checkboxFirstChecked = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        softAssert.assertTrue(checkboxFirstChecked,  "ошибка, после изменения первый чекбокс остался в состоянии unchecked");
        softAssert.assertAll();
    }

    @Test
    public void checkSecondCheckboxState() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        boolean checkboxSecondUnchecked = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        softAssert.assertTrue(checkboxSecondUnchecked, "ошибка, по дефолту второй чекбокс в состоянии unchecked");
        driver.findElements(By.cssSelector("[type=checkbox]")).get(1).click();
        boolean checkboxSecondChecked = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        softAssert.assertFalse(checkboxSecondChecked, "ошибка, после изменения второй чекбокс остался в состоянии checked");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
