import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class TyposTest {
    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу https://the-internet.herokuapp.com/typos
    2. Проверить орфографию текста параграфов
    3. Обновить страницу 10 раз, проверяя орфаграфию параграфов каждый раз

    Посткондишн: закрыть браузер

    Ожидаемый результат:
    - на каждом обновлении текст соответсвует следующему содержанию:
    параграф 1: This example demonstrates a typo being introduced. It does it randomly on each page load.
    параграф 2: Sometimes you'll see a typo, other times you won't.
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
    public void checkTextSpelling(){
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/typos");
        String expectedParagraphFirst = "This example demonstrates a typo being introduced. It does it randomly on each page load.";
        String expectedParagraphSecond = "Sometimes you'll see a typo, other times you won't.";
        for (int i = 0; i < 10; i++){
            WebElement paragraphFirst = driver.findElements(By.tagName("p")).get(0);
            WebElement paragraphSecond = driver.findElements(By.tagName("p")).get(1);
            softAssert.assertEquals(paragraphFirst.getText(), expectedParagraphFirst, "Ошибка в первом параграфе при " + i + " обновлении страницы");
            softAssert.assertEquals(paragraphSecond.getText(), expectedParagraphSecond, "Ошибка во втором параграфе при " + i + " обновлении страницы");
            driver.navigate().refresh();
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
