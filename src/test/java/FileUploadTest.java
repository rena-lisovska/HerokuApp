import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class FileUploadTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
    }

    @Test
    public void checkFileUploadName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/upload");
        String filePath = System.getProperty("user.dir") + "/src/main/resources/New_upload_file.txt";
        String expectedFileName = "New_upload_file.txt";
        driver.findElement(By.xpath("//input[@type='file' and @id='file-upload']")).sendKeys(filePath);
        driver.findElement(By.xpath("//input[@class='button' and @id='file-submit']")).click();
        WebElement uploadedFileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#uploaded-files")));
        assertEquals(uploadedFileName.getText(), expectedFileName, "The file names do not match.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
