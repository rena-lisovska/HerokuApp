import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class AddRemoveElementsTest {

    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу http://the-internet.herokuapp.com/add_remove_elements/
    2. Нажать 2 раза на кнопку Add Element
    3. Удалить кнопку Delete, которая была добавлена 2-й
    4. Проверить количество элементов Delete на странице

    Посткондишн: закрыть браузер

    Ожидаемый результат: на странице остаётся одна кнопка Delete (первая)
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
    public void checkAddRemoveElements() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        int countDeleteElement = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(countDeleteElement, 2, "ошибка, количество кнопок Delete != 2");
        driver.findElements(By.xpath("//button[text()='Delete']")).get(1).click();
        countDeleteElement = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(countDeleteElement, 1, "ошибка, кнопка Delete не была удалена");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
