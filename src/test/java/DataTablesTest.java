import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class DataTablesTest {

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
    1. Открыть страницу https://the-internet.herokuapp.com/tables
    2. Найти значение из таблицы №1, колонка №1, строка №4

    Посткондишн: закрыть браузер

    Ожидаемый результат: Conway
    */

    @Test
    public void checkLastName() {
        driver.get("https://the-internet.herokuapp.com/tables");
        String foughtValueInLastNameColumn = driver.findElement(By.xpath("//table//tr[4]//td[1] ")).getText();
        Assert.assertEquals(foughtValueInLastNameColumn, "Conway");
    }

    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу https://the-internet.herokuapp.com/tables
    2. Найти значение из таблицы №2, колонка №2, строка №3

    Посткондишн: закрыть браузер

    Ожидаемый результат: Jason
    */

    @Test
    public void checkFirstName() {
        driver.get("https://the-internet.herokuapp.com/tables");
        String thirdValueInFirstNameColumn = driver.findElement(By.xpath("//table[2]//tr[3]//td[2]")).getText();
        Assert.assertEquals(thirdValueInFirstNameColumn, "Jason");
    }

    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу https://the-internet.herokuapp.com/tables
    2. Найти значение из таблицы №1, колонка №3, строка №2

    Посткондишн: закрыть браузер

    Ожидаемый результат: fbach@yahoo.com
    */

    @Test
    public void checkEmail() {
        driver.get("https://the-internet.herokuapp.com/tables");
        String secondValueInEmailColumn = driver.findElement(By.xpath("//table[1]//tr[2]//td[3]")).getText();
        Assert.assertEquals(secondValueInEmailColumn, "fbach@yahoo.com");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
