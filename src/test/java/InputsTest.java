import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу https://the-internet.herokuapp.com/inputs и кликнуть на поле Number
    2. На клавиатуре нажать кнопку Keys.ARROW_UP

    Посткондишн: закрыть браузер

    Ожидаемый результат: в поле воявится цифра  1
    */

    @Test
    public void checkNumberInputArrowUp() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputElement = driver.findElement(By.tagName("input"));
        inputElement.clear();
        inputElement.click();
        inputElement.sendKeys(Keys.ARROW_UP);
        String positiveValue = inputElement.getAttribute("value");
        softAssert.assertEquals(positiveValue, "1");
        softAssert.assertAll();
    }

    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу https://the-internet.herokuapp.com/inputs и кликнуть на поле Number
    2. На клавиатуре нажать кнопку Keys.ARROW_DOWN

    Посткондишн: закрыть браузер

    Ожидаемый результат: в поле воявится цифра 1 со знаком минус (-1)
    */

    @Test
    public void checkNumberInputArrowDown() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputElement = driver.findElement(By.tagName("input"));
        inputElement.clear();
        inputElement.click();
        inputElement.sendKeys(Keys.ARROW_DOWN);
        String negativeValue = inputElement.getAttribute("value");
        softAssert.assertEquals(negativeValue, "-1");
        softAssert.assertAll();
    }

    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу https://the-internet.herokuapp.com/inputs и кликнуть на поле Number
    2. Ввести в поле "Have a nice day"

    Посткондишн: закрыть браузер

    Ожидаемый результат: поле останется null
    */

    @Test
    public void checkNonNumberInput() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputElement = driver.findElement(By.tagName("input"));
        inputElement.clear();
        inputElement.click();
        inputElement.sendKeys("Have a nice day");
        String nonNumberValue = inputElement.getAttribute("value");
        softAssert.assertEquals(nonNumberValue, "");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
